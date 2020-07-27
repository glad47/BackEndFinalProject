package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.vo.ReCaptchaVerifyVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.AuthService;
import com.jugu.www.pcbonlinev2.service.MailSendService;
import com.jugu.www.pcbonlinev2.utils.ReCaptchaUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;

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

    private final AuthService authService;

    private final ReCaptchaUtil reCaptchaUtil;

    private final MailSendService mailSendService;

    @Autowired
    public AuthController(AuthService authService, ReCaptchaUtil reCaptchaUtil, MailSendService mailSendService) {
        this.authService = authService;
        this.reCaptchaUtil = reCaptchaUtil;
        this.mailSendService = mailSendService;
    }


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
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 2004, message = "google校验失败")
    })
    @PostMapping(value = "/auth/login")
    public ResponseResult login(@NotNull String username, @NotNull String password, @NotNull String recaptchaResponse){
//        ReCaptchaVerifyVO verifyResult = reCaptchaUtil.verifyToken(recaptchaResponse);
//        if (!verifyResult.getSuccess()){
//            return ResponseResult.failure(ErrorCodeEnum.RE_CAPTCHA_ERROR);
//        }
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
            ),
            @ApiImplicitParam(
                    name = "invite",
                    value = "邀请码",
                    paramType = "query",
                    dataType = "string",
                    example = "a120"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
            @ApiResponse(code = 2004, message = "google校验失败")
    })
    @PostMapping(value = "/auth/register")
    public ResponseResult register(
            @NotNull String username,
            @NotNull String password,
            @NotNull String recaptchaResponse,
            String invite){
//        ReCaptchaVerifyVO verifyResult = reCaptchaUtil.verifyToken(recaptchaResponse);
//        if (!verifyResult.getSuccess()){
//            return ResponseResult.failure(ErrorCodeEnum.RE_CAPTCHA_ERROR);
//        }

        //获取激活码token

        mailSendService.asyncSendRegisterMail(username);
        int save = authService.register(username, password,invite);
        if (save == 1){
            return ResponseResult.success("注册成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    @ApiOperation(
            value = "激活",
            notes = "激活接口",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiImplicitParam(
            name = "token",
            value = "token",
            required = true,
            paramType = "path",
            dataType = "string"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping("/{token}")
    public ResponseResult active(@NotNull @PathVariable("token") String token){
        if(authService.activeUser(token)){
            return ResponseResult.success("激活成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }
}
