package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 快递报价配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("courier_quote")
public class CourierQuoteDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 分区id
	 */
		private Integer partitionId;
	/**
	 *  小包裹0.5－5kg
	 */
		private BigDecimal smallPackage5kg;
	/**
	 *  小包裹0.5－5kg续重
	 */
		private BigDecimal smallPackage5kgadd;
	/**
	 * 大件包裹5.5－10kg
	 */
		private BigDecimal smallPackage10kg;
	/**
	 * 大件包裹5.5－10kg续重
	 */
		private BigDecimal smallPackage10kgadd;
	/**
	 * 大件包裹10.5-20kg
	 */
		private BigDecimal smallPackage20kg;
	/**
	 * 大件包裹10.5-20kg续重
	 */
		private BigDecimal smallPackage20kgadd;
	/**
	 * 大货21－30kg
	 */
		private BigDecimal bigPackage30kg;
	/**
	 * 大货31－50kg
	 */
		private BigDecimal bigPackage50kg;
	/**
	 * 大货51－69kg
	 */
		private BigDecimal bigPackage69kg;
	/**
	 * 大货70－99kg
	 */
		private BigDecimal bigPackage99kg;
	/**
	 * 大货100－196kg
	 */
		private BigDecimal bigPackage196kg;
	/**
	 * 大货197-296kg
	 */
		private BigDecimal bigPackage296kg;
	/**
	 * 大货297-499kg
	 */
		private BigDecimal bigPackage499kg;
	/**
	 * 大货500kg以上
	 */
		private BigDecimal bigPackage500kg;
	/**
	 * 运输时间
	 */
		private String shippingTime;

}
