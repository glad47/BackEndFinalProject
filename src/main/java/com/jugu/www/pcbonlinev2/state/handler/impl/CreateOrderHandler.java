package com.jugu.www.pcbonlinev2.state.handler.impl;

import com.jugu.www.pcbonlinev2.state.annotation.StateHandler;
import com.jugu.www.pcbonlinev2.state.constant.Event;
import com.jugu.www.pcbonlinev2.state.handler.AbstractStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@StateHandler
public class CreateOrderHandler extends AbstractStateHandler {
    public CreateOrderHandler() {
        super.setStateHandlerMark(Event.CREATE_EVENT.status);
    }

    @Override
    protected boolean doHandler(Object o) {
        log.info("执行创建正式订单业务方法。。。。。。。。。。。。");
        return true;
    }

    @Override
    public Event event() {
        return Event.CREATE_EVENT;
    }
}
