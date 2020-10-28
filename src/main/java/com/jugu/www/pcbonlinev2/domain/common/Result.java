package com.jugu.www.pcbonlinev2.domain.common;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class Result implements Serializable {
    private static final long serialVersionUID = -4851302396764126880L;

    private boolean isSuccess;

    private int id;

    //聚谷型号
    private String pns;

    //消息类型 1下单 2支付完成
    private int msgType;

    //支付金额
    private BigDecimal total;

}
