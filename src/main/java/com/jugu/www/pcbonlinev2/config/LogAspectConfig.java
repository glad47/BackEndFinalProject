package com.jugu.www.pcbonlinev2.config;


import com.jugu.www.pcbonlinev2.utils.IPUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 日志切面记录
 */
@Aspect
@Component
@Slf4j
@Order(1)
public class LogAspectConfig {

    @Pointcut("execution(public * com.jugu.www.pcbonlinev2.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接收请求参数。记录日志
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        String s = null;
        if (method.isAnnotationPresent(ApiOperation.class)) {
            ApiOperation apiOperation = method.getAnnotation(ApiOperation.class);
            s = apiOperation.value();
        }

        //记录如下日志
        log.info("业务->[{}],请求URL->[{}],请求类型->[{}],IP->[{}],处理类->[{}],方法参数->[{}]",
                s,
                request.getRequestURL().toString(),
                request.getMethod(),
                IPUtils.getIpAddr(request),
                joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName(),
                Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    private void doAfterReturning(Object ret){
        log.info("返回结果 <<<<<<< [{}]", ret);
    }
}
