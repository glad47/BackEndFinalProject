package com.jugu.www.pcbonlinev2.domain.dto.order;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ApiModel(value = "到支付页面使用的参数")
public class ToPaymentParameterDTO implements Serializable {

    private static final long serialVersionUID = -65569027483673474L;

//    @Valid
//    @ApiModelProperty(value = "订单数据")
//    private List<OrderDetails> orderDetailsList;

    @NotNull(message = "receiverAddressId不能为空")
    @ApiModelProperty(value = "送货地址id")
    private Integer receiverAddressId;

    @NotNull
    @ApiModelProperty(value = "运费")
    private BigDecimal shipping;

    @NotNull(message = "totalWeight不能为空")
    @ApiModelProperty(value = "总重量")
    private String totalWeight;

    @NotNull(message = "subtotal不能为空")
    @ApiModelProperty(value = "总合计(多个报价项的合计和)")
    private BigDecimal subtotal;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userId;

}
