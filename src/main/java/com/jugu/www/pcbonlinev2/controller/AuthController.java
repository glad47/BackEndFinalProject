package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.common.Result;
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
        ReCaptchaVerifyVO verifyResult = reCaptchaUtil.verifyToken(recaptchaResponse);
        if (!verifyResult.getSuccess()){
            return ResponseResult.failure(ErrorCodeEnum.RE_CAPTCHA_ERROR);
        }
        return authService.login(username,password);
    }

    @ApiOperation(
            value = "google登录",
            notes = "google登录接口",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "gid",
                    value = "google授权后的id",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    example = "111111"
            ),
            @ApiImplicitParam(
                    name = "username",
                    value = "用户名",
                    required = false,
                    paramType = "query",
                    dataType = "string",
                    example = "123456"
            ),
            @ApiImplicitParam(
                    name = "email",
                    value = "用户邮箱",
                    required = true,
                    paramType = "query",
                    dataType = "string",
                    example = "123456"
            ),
            @ApiImplicitParam(
                    name = "favicon",
                    value = "用户头像",
                    required = false,
                    paramType = "query",
                    dataType = "string"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功"),
    })
    @PostMapping(value = "/auth/googlelogin")
    public ResponseResult loginInGoogle(@NotNull String gid, String username, @NotNull String email, String favicon) {
        return ResponseResult.success(authService.loginByGoogle(gid,username,email,favicon));
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
        ReCaptchaVerifyVO verifyResult = reCaptchaUtil.verifyToken(recaptchaResponse);
        if (!verifyResult.getSuccess()){
            return ResponseResult.failure(ErrorCodeEnum.RE_CAPTCHA_ERROR);
        }

        //获取激活码token
        mailSendService.asyncSendRegisterMail(username);
        Result save = authService.register(username, password,invite);
        if (save.isSuccess()){
            return ResponseResult.success("注册成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    @ApiOperation(
            value = "激活",
            notes = "激活接口",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "token",
            value = "token",
            required = true,
            paramType = "query",
            dataType = "string"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/active")
    public ResponseResult active(@NotNull String token){
        if(authService.activeUser(token)){
            return ResponseResult.success("激活成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }


    @ApiOperation(
            value = "发送重置密码邮件",
            notes = "发送重置密码邮件接口",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "token",
            value = "token",
            required = true,
            paramType = "query",
            dataType = "string"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/requestPasswordResetByEmail")
    public ResponseResult requestPasswordResetByEmail(@NotNull String email, @NotNull String recaptchaResponse){
        ReCaptchaVerifyVO verifyResult = reCaptchaUtil.verifyToken(recaptchaResponse);
        if (!verifyResult.getSuccess()){
            return ResponseResult.failure(ErrorCodeEnum.RE_CAPTCHA_ERROR);
        }
        if (authService.isExistByEmail(email)){
            mailSendService.asyncSendPasswordResetMail(email);
            return ResponseResult.success("已发送邮件");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.USER_NOT_ERROR);
        }

    }



    @ApiOperation(
            value = "重置密码",
            notes = "重置密码接口",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "token",
                    value = "token",
                    required = true,
                    paramType = "query",
                    dataType = "string"
            ),
            @ApiImplicitParam(
                    name = "nowPwd",
                    value = "新密码",
                    required = true,
                    paramType = "query",
                    dataType = "string"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/requestPasswordReset")
    public ResponseResult requestPasswordReset(@NotNull String token,@NotNull String nowPwd){
        if (authService.passwordReset(token,nowPwd)){
            return ResponseResult.success("修改成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }

}
