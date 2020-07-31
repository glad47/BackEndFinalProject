package com.jugu.www.pcbonlinev2.statehandler;

import com.jugu.www.pcbonlinev2.domain.dto.OrderDTO;
import com.jugu.www.pcbonlinev2.statemachine.OrderStateMachine;

/**
 * 状态处理抽象类
 */
public abstract class AbstractStateHandler implements StateHandler {

    @Override
    public final void handler(OrderDTO orderDTO) {
        this.before(orderDTO);
        Object context = this.doHandler(orderDTO);
        orderDTO.setState(new OrderStateMachine().next(orderDTO.getState(),this.event(),context));
        this.after(orderDTO);
    }

    /**
     * 实际的业务处理方法
     * @param orderDTO
     * @return
     */
    protected abstract Object doHandler(OrderDTO orderDTO);

    /**
     * 在状态修改之前执行
     * @param orderDTO
     */
    protected void before(OrderDTO orderDTO) {

    }

    /**
     * 在状态修改之后执行
     * @param orderDTO
     */
    protected void after(OrderDTO orderDTO) {

    }

}
