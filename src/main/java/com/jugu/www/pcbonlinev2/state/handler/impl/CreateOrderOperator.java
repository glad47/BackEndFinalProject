package com.jugu.www.pcbonlinev2.state.handler.impl;

import com.jugu.www.pcbonlinev2.state.annotation.OrderOperator;
import com.jugu.www.pcbonlinev2.state.constant.OrderStatusEnum;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderOperator;
import org.springframework.stereotype.Component;

@Component
@OrderOperator
public class CreateOrderOperator extends AbstractOrderOperator {
    public CreateOrderOperator() {
        super.setStatus(OrderStatusEnum.CREATE_EVENT.status);
    }

    @Override
    public int handleEvent(int orderStatus, OrderStatusEnum orderStatusEnum) {
        return 0;
    }
}
