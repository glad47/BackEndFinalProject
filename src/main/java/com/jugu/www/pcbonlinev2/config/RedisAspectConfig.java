package com.jugu.www.pcbonlinev2.config;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Method;

/**
 * redis缓存切面，防止redis宕机影响正常业务逻辑
 */
@Aspect
@Configuration
@Order(2)
@Slf4j
public class RedisAspectConfig {

    @SneakyThrows
    @Around("execution(* com.jugu.www.pcbonlinev2.utils.RedisUtil.*(..))")
    public Object around(ProceedingJoinPoint point){
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        Object result = null;

        try {
            result = point.proceed();
        }catch (Throwable e){
            //有CacheException注解的方法需要抛出异常
            if (method.isAnnotationPresent(RedisCacheException.class)){
                throw e;
            }else{
                log.error("redis error",e);
            }
        }

        return result;

    }


}
