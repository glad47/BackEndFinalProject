package com.jugu.www.pcbonlinev2.config;


import java.lang.annotation.*;

/**
 * 注解的缓存方法会抛出异常
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RedisCacheException{
}
