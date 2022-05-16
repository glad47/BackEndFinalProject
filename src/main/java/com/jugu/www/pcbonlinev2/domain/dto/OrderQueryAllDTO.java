package com.jugu.www.pcbonlinev2.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@ApiModel(value = "订单查询DTO")
public class OrderQueryAllDTO implements Serializable {

    private static final long serialVersionUID = 7832816792683955424L;

    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;

    @ApiModelProperty(value = "gerber文件名")
    private String gerberName;

    @ApiModelProperty(value = "状态(1待审核 2已审核待支付 3已支付 4待出货  5待运输 6 订单完成 7订单取消 8订单投诉)")
    private Integer status;

    @ApiModelProperty(value = "订单类型 (0 pcb 1 stencil 2 assembly)")
    private Integer orderType;


}
