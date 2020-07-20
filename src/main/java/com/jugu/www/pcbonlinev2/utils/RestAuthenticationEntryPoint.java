package com.jugu.www.pcbonlinev2.utils;

import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.utils.HttpContextUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器错误处理
 * AccessDeniedHandler 用来解决认证过的用户访问无权限资源时的异常
 */
@Component
public class RestAuthenticationEntryPoint implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException {
        HttpContextUtil.resTokenError(httpServletResponse,httpServletRequest, ErrorCodeEnum.AUTH_UNAUTHORISED);
    }

}
