package com.jugu.www.pcbonlinev2.statemachine;

/**
 * 状态机
 */
public interface StateMachine {
    /**
     * 获取下一个状态
     * @param state 当前状态
     * @param event 动作
     * @param context 上下文条件
     * @return 下一个状态
     */
    State next (State state, Event event, Object context);
}
