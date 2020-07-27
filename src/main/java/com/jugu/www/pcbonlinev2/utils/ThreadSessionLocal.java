package com.jugu.www.pcbonlinev2.utils;

import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadSessionLocal {
    private static ThreadLocal<UserDetailsDTO> threadLocal = new ThreadLocal<>();

    /**
     * 设置用户信息
     *
     * @param userInfo
     */
    public static void setUserInfo( UserDetailsDTO userInfo )
    {
        threadLocal.set(userInfo) ;
        log.info("存入用户信息:{}",userInfo.toString());
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public static UserDetailsDTO getUserInfo()
    {
        log.info("当前线程id：{}",Thread.currentThread().getName());
        return threadLocal.get();
    }
    /**
     * 删除掉储存的用户信息
     */
    public static void remove(){
        threadLocal.remove();
    }
}
