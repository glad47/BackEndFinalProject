package com.jugu.www.pcbonlinev2.state.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum OrderStatusEnum {

    CREATE_EVENT(1,"创建订单"),
    FORMAL_EVENT(2, "正式订单"),
    NEED_PAY(3, "待支付"),
    PAY_DONE(4, "已支付"),
    ORDER_FINISHED(5, "订单已完成"),

    ORDER_CANCEL(6, "订单已取消");


    public int status;
    public String desc;
}
