package com.jugu.www.pcbonlinev2.utils;

import com.google.gson.Gson;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpContextUtil {

    public static void resTokenError(HttpServletResponse res, HttpServletRequest req, ErrorCodeEnum errorCodeEnum) throws IOException {
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin", req.getHeader("Origin"));
        res.setHeader("Content-Type","application/json; charset=utf-8");
        String json = new Gson().toJson(ResponseResult.failure(errorCodeEnum));
//        res.getOutputStream().println(json);
        res.getWriter().print(json);
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

}
