package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.service.AuthService;
import com.jugu.www.pcbonlinev2.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/auth/login")
    public ResponseResult login(@NotNull String username, @NotNull String password){
//        password = SHA256Util.getSHA256StrJava(password+"password");
        System.out.println("username===>"+username);
        System.out.println("paw===>"+password);
        return authService.login(username,password);
    }
}
