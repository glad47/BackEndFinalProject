package com.jugu.www.pcbonlinev2.service;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.common.Result;

public interface AuthService {
    ResponseResult login(String username, String password);

    Result register(String username, String password, String invite);

    boolean activeUser(String token);

    boolean isExistByEmail(String email);

    boolean passwordReset(String token, String nowPwd);

    String loginByGoogle(String gid, String username, String email, String favicon);
}
