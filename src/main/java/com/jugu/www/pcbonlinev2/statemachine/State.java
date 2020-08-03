package com.jugu.www.pcbonlinev2.statemachine;

/**
 * 状态
 */
public enum State {
    pending_audit,          //待审核
    pending_payment,        //待支付
    already_paid,           //已支付
    pending_shipped,        //待出货
    already_shipped,        //已发货
    received                //已收货
}
