package com.jugu.www.pcbonlinev2.service;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;

public interface AuthService {
    ResponseResult login(String username, String password);

    int register(String username, String password, String invite);

    boolean activeUser(String token);

    boolean isExistByEmail(String email);

    boolean passwordReset(String token, String nowPwd);
}
