package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
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

import java.util.List;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private BusinessUserMapper businessUserMapper;

    @Autowired
    private RedisUtil redisUtil;

    private static final String PASSWORD_KEY = "password";

    private static Integer pos = 0;

    @Override
    public ResponseResult login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username,password);

        Authentication authenticate = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseResult.success(token);
    }

    @Override
    public int register(String username, String password, String invite) {
        UserDO query = new UserDO();
        query.setEmail(username);
        UserDO userDO = userMapper.selectOne(new QueryWrapper<UserDO>(query));
        if (userDO != null && userDO.getId() != null) throw new BusinessException(ErrorCodeEnum.PARAM_EMAIL_ERROR);

        query.setPassword(SHA256Util.getSHA256StrJava(password)+PASSWORD_KEY);
        //绑定跟单员
        List<BusinessUserDO> businessUserDOList = businessUserMapper.selectList(null);

        BusinessUserDO businessUser =  getBusinessUserByRoundRobin(businessUserDOList);

        if(businessUser != null){
            query.setBusinessId(businessUser.getBusinessId());
            query.setBusinessName(businessUser.getName());
            query.setUserSystemId(getCustomerNo(businessUser));
        }

        return userMapper.insert(query);
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
