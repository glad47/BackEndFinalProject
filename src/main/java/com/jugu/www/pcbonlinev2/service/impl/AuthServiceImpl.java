package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.aspect.CouponGrant;
import com.jugu.www.pcbonlinev2.aspect.RedisCacheException;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.common.Result;
import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessUserDO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.mapper.BusinessUserMapper;
import com.jugu.www.pcbonlinev2.mapper.UserMapper;
import com.jugu.www.pcbonlinev2.service.AuthService;
import com.jugu.www.pcbonlinev2.service.UserService;
import com.jugu.www.pcbonlinev2.utils.JwtTokenUtil;
import com.jugu.www.pcbonlinev2.utils.RedisUtil;
import com.jugu.www.pcbonlinev2.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserMapper userMapper;

    private final UserService userService;

    private final BusinessUserMapper businessUserMapper;

    private final RedisUtil redisUtil;

    private static final String PASSWORD_KEY = "password";

    private static Integer pos = 0;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserMapper userMapper, UserService userService, BusinessUserMapper businessUserMapper, RedisUtil redisUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
        this.userService = userService;
        this.businessUserMapper = businessUserMapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public ResponseResult login(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);
        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);

        String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseResult.success(token);
    }


    @Override
    @CouponGrant
    public Result register(String username, String password, String invite) {

        if (isExistByEmail(username)) throw new BusinessException(ErrorCodeEnum.PARAM_EMAIL_ERROR);

        UserDO userDO = new UserDO();

        if(!StringUtils.isEmpty(invite)){
            BusinessUserDO businessUserDO = businessUserMapper.selectOne(new QueryWrapper<BusinessUserDO>().eq("prefix_no", invite.trim()).last("limit 1"));
            if (businessUserDO != null){
                userDO.setBusinessId(businessUserDO.getBusinessId());
                userDO.setBusinessName(businessUserDO.getName());
                userDO.setUserSystemId(getCustomerNo(businessUserDO));
            }else{
                throw new BusinessException(ErrorCodeEnum.PARAM_BUSINESS_CODE_ERROR);
            }
        }else{
            //绑定跟单员
            fullBusinessUserInfo(userDO);
        }

        //设置未激活
        userDO.setInvalidMark(1);
        userDO.setEmail(username);
        userDO.setPassword(SHA256Util.getSHA256StrJava(password+PASSWORD_KEY));
        boolean b = userService.save(userDO);

        return Result.builder().id(userDO.getId()).isSuccess(b).build();
    }

    protected void fullBusinessUserInfo(UserDO userDO) {
        //获取有效的跟单员过滤下
        List<BusinessUserDO> businessUserDOList = businessUserMapper.selectList(new QueryWrapper<BusinessUserDO>().eq("role_id",10).eq(" `status`",1));
        BusinessUserDO businessUser = getBusinessUserByRoundRobin(businessUserDOList);
        if (businessUser != null) {
            userDO.setBusinessId(businessUser.getBusinessId());
            userDO.setBusinessName(businessUser.getName());
            userDO.setUserSystemId(getCustomerNo(businessUser));
        }
    }

    /**
     * 激活用户
     *
     * @param token 令牌
     */
    @Override
    public boolean activeUser(String token) {
        UserDO userDO = new UserDO();
        userDO.setInvalidMark(0);
        return validateTokenAndUserUpdate(token,userDO);
    }

    @Override
    public boolean isExistByEmail(String email) {
        Integer existCount = userMapper.selectCount(new QueryWrapper<UserDO>().eq("email",email).last("limit 1"));
        return existCount >= 1;
    }

    /**
     * 修改密码
     *
     * @param token 令牌
     * @param nowPwd
     */
    @Override
    public boolean passwordReset(String token, String nowPwd) {
        UserDO userDO = new UserDO();
        userDO.setPassword(SHA256Util.getSHA256StrJava(nowPwd+PASSWORD_KEY));
        return validateTokenAndUserUpdate(token,userDO);
    }

    /**
     * google 登录
     *
     * @param gid
     * @param username
     * @param email
     * @param favicon
     * @return
     */
    @Override
    public String loginByGoogle(String gid, String username, String email, String favicon) {
        //判断是否存在gid
        Integer existCount = userMapper.selectCount(new QueryWrapper<UserDO>().eq("google_id", gid).last("limit 1"));
        if (existCount >= 1){
            //存在
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            return jwtTokenUtil.generateToken(userDetails);
        }else{
            //不存在创建一个用户在返回
            UserDO userDOByGoogle = new UserDO();
            userDOByGoogle.setGoogleId(gid);
            userDOByGoogle.setUserName(username);
            userDOByGoogle.setEmail(email);
            userDOByGoogle.setFavicon(favicon);
            userDOByGoogle.setInvalidMark(0);

            fullBusinessUserInfo(userDOByGoogle);

            boolean save = userService.save(userDOByGoogle);
            if (save){
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                return jwtTokenUtil.generateToken(userDetails);
            }
            return null;

        }
    }

    private String getCustomerNo(BusinessUserDO businessUser) {
        return redisUtil.SeqGenerator(businessUser.getPrefixNo(),2,RedisUtil.NOT_EXPIRE);
    }

    private Boolean validateTokenAndUserUpdate(String token, UserDO userDO) {
        String userEmail = jwtTokenUtil.getUsernameFromToken(token);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUsername(userEmail);

        if (!jwtTokenUtil.validateToken(token, userDetailsDTO)) throw new BusinessException(ErrorCodeEnum.PARAM_AUTH_ERROR);

        return userMapper.update(userDO,new QueryWrapper<UserDO>().eq("email",userEmail)) == 1;

    }

    @RedisCacheException
    private BusinessUserDO getBusinessUserByRoundRobin(List<BusinessUserDO> businessUserDOList) {
        BusinessUserDO businessUser;
        //noinspection SynchronizeOnNonFinalField
        synchronized (pos){
            businessUser = businessUserDOList.get(redisUtil.saveValueAndIncrementByRoundRobinListSize("RoundRobinUserIndex",businessUserDOList.size()));
            pos++;
        }

        return businessUser;
    }
}
