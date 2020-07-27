package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.mapper.UserMapper;
import com.jugu.www.pcbonlinev2.utils.ThreadSessionLocal;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO query = new UserDO();
        query.setEmail(username);
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(query);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        return Optional.ofNullable(userDO)
                .map(u ->{
                    UserDetailsDTO result = new UserDetailsDTO();
                    result.setId(u.getId());
                    result.setPassword(u.getPassword());
                    result.setUsername(u.getEmail());
                    result.setInvalidMark(u.getInvalidMark());

                    //把userDetail塞入线程session
                    ThreadSessionLocal.setUserInfo(result);
                    return result;
                })
                .orElseThrow(()-> new BusinessException(ErrorCodeEnum.USER_NOT_ERROR));

    }
}
