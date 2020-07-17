package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 支付日志
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("pay_log")
public class PayLogDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Long id;
	/**
	 * 创建时间
	 */
		private Date createDate;
	/**
	 * 交易状态
	 */
		private String paymentStatus;
	/**
	 * 交易时间
	 */
		private String paymentDate;
	/**
	 * 交易id
	 */
		private String txnId;
	/**
	 * 父交易id
	 */
		private String parentTxnId;
	/**
	 * 收款人email
	 */
		private String receiverEmail;
	/**
	 * 付款人email
	 */
		private String payerEmail;
	/**
	 * 交易金额
	 */
		private String mcGross;
	/**
	 * payPal 手续费
	 */
		private String paymentFee;
	/**
	 * 总净额 交易金额 - 手续费
	 */
		private BigDecimal totalNet;
	/**
	 * 姓
	 */
		private String firstName;
	/**
	 * 名
	 */
		private String lastName;
	/**
	 * 收款人id
	 */
		private String receiverId;
	/**
	 * 付款人id
	 */
		private String payerId;
	/**
	 * 自定义字段
	 */
		private String custom;
	/**
	 * 跟单员名字
	 */
		private String businessName;
	/**
	 * 币种
	 */
		private String currency;
	/**
	 * 客户水单截图
	 */
		private String customerMemoScreenshots;
	/**
	 * 银行到账截图
	 */
		private String bankPayScreenshots;

}
