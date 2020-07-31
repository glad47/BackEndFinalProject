package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import com.jugu.www.pcbonlinev2.statemachine.State;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class OrderDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "主键(不参与业务)",hidden = true)
    private Integer id;
    @ApiModelProperty(value = "订单id(使用paypay返回的orderid)", hidden = true)
    private String orderId;
    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userId;
    @ApiModelProperty(value = "订单合同编号（日期+日接单数）")
    private String orderno;
    @ApiModelProperty(value = "客户填写的订单号")
    private String corderNo;
    @ApiModelProperty(value = "送货地址id")
    private Integer receiverAddressId;
    @ApiModelProperty(value = "重量")
    private String weight;
    @ApiModelProperty(value = "邮费")
    private BigDecimal postFee;
    @ApiModelProperty(value = "总合计(多个报价项的合计和)")
    private BigDecimal totalSubtotal;
    @ApiModelProperty(value = "扣除优惠后的总价格")
    private BigDecimal amountFee;
    @ApiModelProperty(value = "paypal手续费")
    private BigDecimal paypalFee;
    @ApiModelProperty(value = "会员优惠值")
    private String disMemberStr;
    @ApiModelProperty(value = "卡券优惠值")
    private String disCouponStr;
    @ApiModelProperty(value = "总费用")
    private BigDecimal totalFee;
    @ApiModelProperty(value = "支付类型")
    private Integer paymentType;
    @ApiModelProperty(value = "状态(1 待审核 2待付款3已付款)")
    private Integer status;
    @ApiModelProperty(value = "付款时间")
    private Date paymentTime;
    @ApiModelProperty(value = "发货时间")
    private Date consignTime;
    @ApiModelProperty(value = "交易完成时间")
    private Date entTime;
    @ApiModelProperty(value = "交易关闭时间")
    private Date closeTime;
    @ApiModelProperty(value = "运送到那个国家")
    private String destinationCountry;
    @ApiModelProperty(value = "物流名称")
    private String shippingName;
    @ApiModelProperty(value = "物流单号")
    private String shippingCode;
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "制造天数")
    private String buildTime;
    @ApiModelProperty(value = "跟单员名字")
    private String businessName;
    @ApiModelProperty(value = "跟单员id")
    private Integer businessId;

    private State state;

}
