package com.jugu.www.pcbonlinev2.state.handler.impl;

import com.jugu.www.pcbonlinev2.state.annotation.OrderProcessor;
import com.jugu.www.pcbonlinev2.state.constant.State;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderProcessor;
import org.springframework.stereotype.Component;

@Component
@OrderProcessor
public class CreateOrderProcessor extends AbstractOrderProcessor {
    public CreateOrderProcessor() {
        super.setStatus(State.TEMPORARY_ORDER.status);
    }

    @Override
    public boolean process(Integer orderId, Object... params) {
        // TODO: 2020-08-04 实际业务的 实现方法
        System.out.println("修改数据库xxxxx");
        return true;
    }
}
