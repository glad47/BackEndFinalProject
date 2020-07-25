package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO query = new UserDO();
        query.setEmail(username);
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(query);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        UserDetailsDTO result = new UserDetailsDTO();
        result.setId(userDO.getId());
        result.setPassword(userDO.getPassword());
        result.setUsername(userDO.getEmail());
        result.setInvalidMark(userDO.getInvalidMark());
        return result;
    }
}
