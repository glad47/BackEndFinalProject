package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessUserDO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.mapper.BusinessUserMapper;
import com.jugu.www.pcbonlinev2.mapper.UserMapper;
import com.jugu.www.pcbonlinev2.service.AuthService;
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

    private final BusinessUserMapper businessUserMapper;

    private final RedisUtil redisUtil;

    private static final String PASSWORD_KEY = "password";

    private static Integer pos = 0;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtTokenUtil jwtTokenUtil, UserMapper userMapper, BusinessUserMapper businessUserMapper, RedisUtil redisUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
        this.businessUserMapper = businessUserMapper;
        this.redisUtil = redisUtil;
    }

    @Override
    public ResponseResult login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);

        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if(userDetails == null) throw new BusinessException(ErrorCodeEnum.USER_NOT_ERROR);
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseResult.success(token);
    }

    @Override
    public int register(String username, String password, String invite) {
        Integer existCount = userMapper.selectCount(new QueryWrapper<UserDO>().eq("email",username).last("limit 1"));
        if (existCount >= 1) throw new BusinessException(ErrorCodeEnum.PARAM_EMAIL_ERROR);
        
        UserDO userDO = new UserDO();
        userDO.setEmail(username);
        userDO.setPassword(SHA256Util.getSHA256StrJava(password+PASSWORD_KEY));

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
            List<BusinessUserDO> businessUserDOList = businessUserMapper.selectList(null);
            BusinessUserDO businessUser =  getBusinessUserByRoundRobin(businessUserDOList);
            if(businessUser != null){
                userDO.setBusinessId(businessUser.getBusinessId());
                userDO.setBusinessName(businessUser.getName());
                userDO.setUserSystemId(getCustomerNo(businessUser));
            }
        }

        //设置未激活
        userDO.setInvalidMark(1);

        return userMapper.insert(userDO);
    }

    /**
     * 激活用户
     *
     * @param token 令牌
     */
    @Override
    public boolean activeUser(String token) {
        String userEmail = jwtTokenUtil.getUsernameFromToken(token);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUsername(userEmail);

        Boolean validateToken = jwtTokenUtil.validateToken(token, userDetailsDTO);
        if (!validateToken) throw new BusinessException(ErrorCodeEnum.PARAM_AUTH_ERROR);

        UserDO userDO = new UserDO();
        userDO.setInvalidMark(0);

        return userMapper.update(userDO,new QueryWrapper<UserDO>().eq("email",userEmail)) == 1;
    }

    private String getCustomerNo(BusinessUserDO businessUser) {
        return redisUtil.SeqGenerator(businessUser.getPrefixNo(),2,RedisUtil.NOT_EXPIRE);
    }

    private BusinessUserDO getBusinessUserByRoundRobin(List<BusinessUserDO> businessUserDOList) {
        BusinessUserDO businessUser;
        synchronized (pos){
            businessUser = businessUserDOList.get(redisUtil.saveValueAndIncrementByRoundRobinListSize("RoundRobinUserIndex",businessUserDOList.size()));
            pos++;
        }

        return businessUser;
    }
}
