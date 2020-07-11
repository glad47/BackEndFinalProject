package com.jugu.www.pcbonlinev2.config;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LogAspectConfig {

    @Pointcut("execution(public * com.jugu.www.pcbonlinev2.controller..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint){
        //接收请求参数。记录日志
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        //记录如下日志
        log.info("请求URL >>>>> [{}]", request.getRequestURL().toString());
        log.info("请求类型 >>>> [{}]", request.getMethod());
        log.info("IP >>>>> [{}]", request.getRemoteAddr());
        log.info("处理类 >>>>> [{}]", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("方法参数 >>> [{}]", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    private void doAfterReturning(Object ret){
        log.info("返回结果 <<<<<<< [{}]", ret);
    }
}
