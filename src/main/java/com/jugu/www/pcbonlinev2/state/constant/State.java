package com.jugu.www.pcbonlinev2.state.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum State {

//    CREATE_EVENT(1,"创建订单"),
//    FORMAL_EVENT(2, "正式订单"),
//    NEED_PAY(3, "待支付"),



    TEMPORARY_ORDER(1,"临时订单"),
    FORMAL_ORDER(2,"正式订单"),
    PAY_DONE(3, "已付款"),
    PENDING_RECEIVED(4,"待收货"),
    ORDER_FINISHED(5, "订单已完成"),
    ORDER_CANCEL(6, "订单已取消");

    public int status;
    public String desc;
}
