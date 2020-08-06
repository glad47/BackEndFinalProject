//package com.jugu.www.pcbonlinev2.state.handler.impl;
//
//import com.jugu.www.pcbonlinev2.state.annotation.OrderOperator;
//import com.jugu.www.pcbonlinev2.state.constant.State;
//import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderOperator;
//import org.springframework.stereotype.Component;
//
//@Component
//@OrderOperator
//public class CreateOrderOperator extends AbstractOrderOperator {
//    public CreateOrderOperator() {
//        super.setStatus(State.CREATE_EVENT.status);
//    }
//
//    @Override
//    public int handleEvent(int orderStatus, State state) {
//        System.out.println("订单操作类,当前状态："+orderStatus+"操作类型:"+ state.desc+",下一个状态:"+ State.FORMAL_EVENT.status);
//        return State.FORMAL_EVENT.status;
//    }
//}
