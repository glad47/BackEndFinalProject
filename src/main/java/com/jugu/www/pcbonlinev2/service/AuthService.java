package com.jugu.www.pcbonlinev2.service;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;

public interface AuthService {
    ResponseResult login(String username, String password);
}