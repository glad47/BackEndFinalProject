package com.jugu.www.pcbonlinev2.state.handler;

import lombok.Data;

@Data
public abstract class AbstractStateHandler implements StateHandler {
    /**
     * 状态机处理标识，必须和动作state一致
     */
    int stateHandlerMark;

    @Override
    public boolean handler(Object o) {
        this.before(o);
        boolean result = doHandler(o); 
        this.after(o);
        return result;
    }

    /**
     * 实际业务实现方法
     * @param o 对象
     */
    protected abstract boolean doHandler(Object o);

    /**
     * 状态修改之前运行
     * @param o
     */
    protected void after(Object o){};

    /**
     * 状态修改之后运行
     * @param o
     */
    protected void before(Object o){};
}
