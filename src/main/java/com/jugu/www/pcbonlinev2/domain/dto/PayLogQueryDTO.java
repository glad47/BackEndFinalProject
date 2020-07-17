package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 支付日志
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "支付日志")
public class PayLogQueryDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Long id;
		@ApiModelProperty(value = "创建时间")
	private Date createDate;
		@ApiModelProperty(value = "交易状态")
	private String paymentStatus;
		@ApiModelProperty(value = "交易时间")
	private String paymentDate;
		@ApiModelProperty(value = "交易id")
	private String txnId;
		@ApiModelProperty(value = "父交易id")
	private String parentTxnId;
		@ApiModelProperty(value = "收款人email")
	private String receiverEmail;
		@ApiModelProperty(value = "付款人email")
	private String payerEmail;
		@ApiModelProperty(value = "交易金额")
	private String mcGross;
		@ApiModelProperty(value = "payPal 手续费")
	private String paymentFee;
		@ApiModelProperty(value = "总净额 交易金额 - 手续费")
	private BigDecimal totalNet;
		@ApiModelProperty(value = "姓")
	private String firstName;
		@ApiModelProperty(value = "名")
	private String lastName;
		@ApiModelProperty(value = "收款人id")
	private String receiverId;
		@ApiModelProperty(value = "付款人id")
	private String payerId;
		@ApiModelProperty(value = "自定义字段")
	private String custom;
		@ApiModelProperty(value = "跟单员名字")
	private String businessName;
		@ApiModelProperty(value = "币种")
	private String currency;
		@ApiModelProperty(value = "客户水单截图")
	private String customerMemoScreenshots;
		@ApiModelProperty(value = "银行到账截图")
	private String bankPayScreenshots;
	
}
