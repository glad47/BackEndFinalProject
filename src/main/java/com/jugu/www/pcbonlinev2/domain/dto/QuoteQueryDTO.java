package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteQueryDTO implements Serializable {
    private static final long serialVersionUID = 7628632040252542273L;

    @ApiModelProperty(value = "状态(1待审核 2已审核待支付 3已支付 4待出货  5待运输 6 订单完成 7订单取消 8订单投诉)")
    private Integer status; //状态(1待审核 2已审核待支付 3已支付 4待出货  5待运输 6 订单完成 7订单取消 8订单投诉)

    @ApiModelProperty(value = "状态list(这两个参数只需传递一个)")
    private List statusList; //状态list(这两个参数只需传递一个


}
