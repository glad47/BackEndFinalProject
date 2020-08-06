package com.jugu.www.pcbonlinev2.state.handler;


import com.jugu.www.pcbonlinev2.state.constant.Event;

/**
 * 状态实际处理接口
 */
public interface StateHandler {
    /**
     * 实际处理业务方法
     * @return 是否成功
     */
    boolean handler(Object o);

    /**
     * @return 返回处理的动作
     */
    Event event();
}
