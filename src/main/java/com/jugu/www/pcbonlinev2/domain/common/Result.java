package com.jugu.www.pcbonlinev2.domain.common;

import com.jugu.www.pcbonlinev2.domain.dto.order.QuoteInfo;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class Result implements Serializable {
    private static final long serialVersionUID = -4851302396764126880L;

    private boolean isSuccess;

    private String errorMsg;

    private int id;

    //聚谷型号
    private String pns;

    //消息类型 1审核 2支付
    private int msgType;

    //支付金额
    private BigDecimal total;

    //报价信息
    private QuoteInfo quoteInfo;
}
