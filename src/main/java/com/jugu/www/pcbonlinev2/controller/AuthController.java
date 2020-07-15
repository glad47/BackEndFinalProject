package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.vo.ReCaptchaVerifyVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.AuthService;
import com.jugu.www.pcbonlinev2.utils.ReCaptchaUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@Api(
        value = "授权管理Controller",
        tags = {"授权管理Controller"},
        produces = "http, https",
        hidden = false
)
@Validated
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private ReCaptchaUtil reCaptchaUtil;


    @ApiOperation(
            value = "登录",
            notes = "登录接口",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "username",
                    value = "用户邮箱",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    example = "12345@qq.com"
            ),
            @ApiImplicitParam(
                    name = "password",
                    value = "用户密码",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    example = "123456"
            ),
            @ApiImplicitParam(
                    name = "recaptchaResponse",
                    value = "reCaptcha,返回的g-recaptcha-response",
                    required = true,
                    paramType = "query",
                    dataType = "string"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0000, message = "操作成功"),
            @ApiResponse(code = 2004, message = "google校验失败")
    })
    @PostMapping(value = "/auth/login")
    public ResponseResult login(@NotNull String username, @NotNull String password, @NotNull String recaptchaResponse){
        ReCaptchaVerifyVO verifyResult = reCaptchaUtil.verifyToken(recaptchaResponse);
        if (!verifyResult.getSuccess()){
            return ResponseResult.failure(ErrorCodeEnum.RE_CAPTCHA_ERROR);
        }
        return authService.login(username,password);
    }


    @ApiOperation(
            value = "注册",
            notes = "注册接口",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "username",
                    value = "用户邮箱",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    example = "12345@qq.com"
            ),
            @ApiImplicitParam(
                    name = "password",
                    value = "用户密码",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    example = "123456"
            ),
            @ApiImplicitParam(
                    name = "recaptchaResponse",
                    value = "reCaptcha,返回的g-recaptcha-response",
                    required = true,
                    paramType = "query",
                    dataType = "string"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0000, message = "操作成功"),
            @ApiResponse(code = 2004, message = "google校验失败")
    })
    @PostMapping(value = "/auth/register")
    public ResponseResult register(@NotNull String username, @NotNull String password, @NotNull String recaptchaResponse){
//        ReCaptchaVerifyVO verifyResult = reCaptchaUtil.verifyToken(recaptchaResponse);
//        if (!verifyResult.getSuccess()){
//            return ResponseResult.failure(ErrorCodeEnum.RE_CAPTCHA_ERROR);
//        }
        int save = authService.register(username, password);
        if (save == 1){
            return ResponseResult.success("注册成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }
}
