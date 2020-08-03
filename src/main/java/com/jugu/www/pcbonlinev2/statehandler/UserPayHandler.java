package com.jugu.www.pcbonlinev2.statehandler;

import com.jugu.www.pcbonlinev2.domain.dto.OrderDTO;
import com.jugu.www.pcbonlinev2.statemachine.Event;

/**
 * 用户支付操作业务逻辑处理
 */
public class UserPayHandler extends AbstractStateHandler{

    @Override
    protected Object doHandler(OrderDTO orderDTO) {
        System.out.println("用户已支付");
        return null;
    }

    @Override
    public Event event() {
        return Event.pay;
    }

}
