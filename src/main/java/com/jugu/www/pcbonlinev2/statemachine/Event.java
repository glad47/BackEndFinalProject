package com.jugu.www.pcbonlinev2.statemachine;

/**
 * 行为
 */
public enum Event {
    audit,      //审核
    pay,        //支付
    production, //生产
    shipped,    //发货
    receipt     //签收
}
