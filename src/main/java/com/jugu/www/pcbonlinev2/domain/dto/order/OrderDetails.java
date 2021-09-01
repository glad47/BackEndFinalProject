package com.jugu.www.pcbonlinev2.domain.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单支付时明细
 */
@Data
@ApiModel(value = "订单支付明细")
public class OrderDetails implements Serializable {

    private static final long serialVersionUID = 5387786015140694360L;

    @ApiModelProperty(value = "订单项id")
    @NotNull(message = "产品id不能为null")
    private Integer id; //产品id

    @ApiModelProperty(value = "产品编号")
    private String productNo; //产品编号

    @NotNull(message = "type不能为null")
    @ApiModelProperty(value = "产品类型 1pcb 2钢网 3贴片")
    private Integer type; //产品类型 1pcb 2钢网 3贴片

    @ApiModelProperty(value = "产品重量")
    private BigDecimal weight; //产品重量"

    @ApiModelProperty(value = "产品小计")
    private BigDecimal subtotal; //产品小计

    private String qty; //商品数量
}
