package com.jugu.www.pcbonlinev2.utils;

import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.utils.HttpContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 过滤器错误处理
 * AuthenticationEntryPoint 用来解决匿名用户访问无权限资源时的异常
 */
@Component
public class RestAccessDeniedHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        HttpContextUtil.resTokenError(httpServletResponse, httpServletRequest, ErrorCodeEnum.AUTH_ACCESS_DENIED);
    }

}