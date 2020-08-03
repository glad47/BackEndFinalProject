package com.jugu.www.pcbonlinev2.state.handler.impl;

import com.jugu.www.pcbonlinev2.state.annotation.OrderProcessor;
import com.jugu.www.pcbonlinev2.state.constant.OrderStatusEnum;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderProcessor;
import org.springframework.stereotype.Component;

@Component
@OrderProcessor
public class CreateOrderProcessor extends AbstractOrderProcessor {
    public CreateOrderProcessor() {
        super.setStatus(OrderStatusEnum.CREATE_EVENT.status);
    }

    @Override
    public boolean process(Integer orderId, Object... params) {
        return false;
    }
}
