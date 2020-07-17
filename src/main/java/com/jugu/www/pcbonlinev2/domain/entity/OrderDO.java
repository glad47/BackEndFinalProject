package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 订单表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("order")
public class OrderDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * 主键(不参与业务)
	 */
		@TableId
		private Integer id;
	/**
	 * 订单id(使用paypay返回的orderid)
	 */
		private String orderId;
	/**
	 * 用户id
	 */
		private Integer userId;
	/**
	 * 订单合同编号（日期+日接单数）
	 */
		private String orderno;
	/**
	 * 客户填写的订单号
	 */
		private String corderNo;
	/**
	 * 送货地址id
	 */
		private Integer receiverAddressId;
	/**
	 * 重量
	 */
		private String weight;
	/**
	 * 邮费
	 */
		private BigDecimal postFee;
	/**
	 * 总合计(多个报价项的合计和)
	 */
		private BigDecimal totalSubtotal;
	/**
	 * 扣除优惠后的总价格
	 */
		private BigDecimal amountFee;
	/**
	 * paypal手续费
	 */
		private BigDecimal paypalFee;
	/**
	 * 会员优惠值
	 */
		private String disMemberStr;
	/**
	 * 卡券优惠值
	 */
		private String disCouponStr;
	/**
	 * 总费用
	 */
		private BigDecimal totalFee;
	/**
	 * 支付类型
	 */
		private Integer paymentType;
	/**
	 * 状态(1 待审核 2待付款3已付款)
	 */
		private Integer status;
	/**
	 * 付款时间
	 */
		private Date paymentTime;
	/**
	 * 发货时间
	 */
		private Date consignTime;
	/**
	 * 交易完成时间
	 */
		private Date entTime;
	/**
	 * 交易关闭时间
	 */
		private Date closeTime;
	/**
	 * 运送到那个国家
	 */
		private String destinationCountry;
	/**
	 * 物流名称
	 */
		private String shippingName;
	/**
	 * 物流单号
	 */
		private String shippingCode;
	/**
	 * 修改时间
	 */
		private Date gmtModified;
	/**
	 * 创建时间
	 */
		private Date gmtCreate;
	/**
	 * 制造天数
	 */
		private String buildTime;
	/**
	 * 跟单员名字
	 */
		private String businessName;
	/**
	 * 跟单员id
	 */
		private Integer businessId;

}
