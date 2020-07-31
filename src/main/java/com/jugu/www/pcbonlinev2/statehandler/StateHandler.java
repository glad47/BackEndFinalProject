package com.jugu.www.pcbonlinev2.statehandler;

import com.jugu.www.pcbonlinev2.domain.dto.OrderDTO;
import com.jugu.www.pcbonlinev2.statemachine.Event;

/**
 * 状态处理接口
 */
public interface StateHandler {
    void handler(OrderDTO orderDTO);

    Event event();
}
