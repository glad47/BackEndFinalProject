package com.jugu.www.pcbonlinev2.state.constant;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Event {

    CREATE_EVENT(1,"创建", "order"),
    PAY_EVENT(2, "支付", "order"),
    CANCEL_EVENT(3,"取消", "order"),
    SHIPPING_EVENT(4,"发货","order"),
    RECEIVING_EVENT(5,"签收", "order");


    public int status;
    public String desc;
    public String mark;
}
