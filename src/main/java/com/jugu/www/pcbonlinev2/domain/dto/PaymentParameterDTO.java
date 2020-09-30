package com.jugu.www.pcbonlinev2.domain.dto;

import com.jugu.www.pcbonlinev2.domain.dto.order.OrderDetails;
import com.jugu.www.pcbonlinev2.domain.vo.CouponVO;
import com.jugu.www.pcbonlinev2.domain.vo.MemberLevelVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ApiModel(value = "支付后回调参数")
public class PaymentParameterDTO implements Serializable {
    private static final long serialVersionUID = -3993127174799315511L;

    @Valid
    @ApiModelProperty(value = "订单详细")
    private List<OrderDetails> orderDetailsList;

    @ApiModelProperty(value = "此次订单的积分数")
    private BigDecimal totalPrice;

    @NotNull(message = "shipping不能为空")
    @ApiModelProperty(value = "总邮费")
    private BigDecimal shipping;

    @NotNull(message = "subtotal不能为空")
    @ApiModelProperty(value = "总合计(多个报价项的合计和)")
    private BigDecimal subtotal;

    @NotNull(message = "courierCompanyName不能为空")
    @ApiModelProperty(value = "物流名称")
    private String courierCompanyName;

    @NotNull(message = "countryName不能为空")
    @ApiModelProperty(value = "运送到那个国家")
    private String countryName;

    @NotNull(message = "totalWeight不能为空")
    @ApiModelProperty(value = "总重量")
    private String totalWeight;

    @ApiModelProperty(value = "客户填写的订单号，可为空")
    private String orderNo;

    @ApiModelProperty(value = "是否使用优惠劵 默认0没有 1使用")
    private Integer isUseCoupon = 0;

    @ApiModelProperty(value = "优惠金额(字符串类型：-10)")
    private String disCouponStr;

    @ApiModelProperty(value = "会员优惠金额")
    private String disMemberStr;

    @ApiModelProperty(value = "券id")
    private Integer couponId;

    @NotNull(message = "paymentTotal不能为空")
    @ApiModelProperty(value = "实际支付金额=(paypalFee + amount)")
    private BigDecimal paymentTotal;

    @NotNull(message = "paypalFee不能为空")
    @ApiModelProperty(value = "paypal手续费")
    private BigDecimal paypalFee;

    @NotNull(message = "amount不能为空")
    @ApiModelProperty(value = "扣除优惠后的总价格")
    private BigDecimal amount;

    @NotNull(message = "orderNoBySys 不能为空")
    @ApiModelProperty(value = "生成的order订单号")
    private String orderNoBySys;

    @NotNull(message = "payPayOrderId不能为空")
    @ApiModelProperty(value = "payPay返回的orderId")
    private String payPayOrderId;

    @NotNull(message = "receiverAddressId不能为空")
    @ApiModelProperty(value = "送货地址id")
    private Integer receiverAddressId;

    @NotNull(message = "paymentType不能为空")
    @ApiModelProperty(value = "支付类型1->payPal、2->BankTransfer、3->WesternUnion、4->PayWithAccountBalance")
    private Integer paymentType;

    @ApiModelProperty(value = "当前用户的可用优惠卷")
    private List<CouponVO> couponVOList;

    @ApiModelProperty(value = "会员等级信息")
    private MemberLevelVO memberLevelVO;

}
