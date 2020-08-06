//package com.jugu.www.pcbonlinev2.state.handler.impl;
//
//import com.jugu.www.pcbonlinev2.state.constant.State;
//import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderOperator;
//
//public class FormalOrderOperator extends AbstractOrderOperator {
//
//    public FormalOrderOperator() {
//        super.setStatus(State.FORMAL_EVENT.status);
//    }
//
//    @Override
//    public int handleEvent(int orderStatus, State state) {
//        switch (state){
//            case ORDER_CANCEL:
//                return State.ORDER_CANCEL.status;
//            case PAY_DONE:
//                return State.PAY_DONE.status;
//            case NEED_PAY:
//                return State.NEED_PAY.status;
//            default:
//                return getStatus();
//        }
//    }
//}
