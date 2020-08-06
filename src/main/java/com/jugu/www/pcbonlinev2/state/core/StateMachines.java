package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.state.constant.Event;
import com.jugu.www.pcbonlinev2.state.constant.State;

/**
 * 状态机
 */
public interface StateMachines {

    /**
     * 根据当前状态和行为得到下个状态
     * @param currState 当前状态
     * @param event 动作
     * @return 下一个状态
     */
    State next(State currState, Event event);
}
