package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 快递报价配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class CourierQuoteDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "分区id")
	private Integer partitionId;
		@ApiModelProperty(value = " 小包裹0.5－5kg")
	private BigDecimal smallPackage5kg;
		@ApiModelProperty(value = " 小包裹0.5－5kg续重")
	private BigDecimal smallPackage5kgadd;
		@ApiModelProperty(value = "大件包裹5.5－10kg")
	private BigDecimal smallPackage10kg;
		@ApiModelProperty(value = "大件包裹5.5－10kg续重")
	private BigDecimal smallPackage10kgadd;
		@ApiModelProperty(value = "大件包裹10.5-20kg")
	private BigDecimal smallPackage20kg;
		@ApiModelProperty(value = "大件包裹10.5-20kg续重")
	private BigDecimal smallPackage20kgadd;
		@ApiModelProperty(value = "大货21－30kg")
	private BigDecimal bigPackage30kg;
		@ApiModelProperty(value = "大货31－50kg")
	private BigDecimal bigPackage50kg;
		@ApiModelProperty(value = "大货51－69kg")
	private BigDecimal bigPackage69kg;
		@ApiModelProperty(value = "大货70－99kg")
	private BigDecimal bigPackage99kg;
		@ApiModelProperty(value = "大货100－196kg")
	private BigDecimal bigPackage196kg;
		@ApiModelProperty(value = "大货197-296kg")
	private BigDecimal bigPackage296kg;
		@ApiModelProperty(value = "大货297-499kg")
	private BigDecimal bigPackage499kg;
		@ApiModelProperty(value = "大货500kg以上")
	private BigDecimal bigPackage500kg;
		@ApiModelProperty(value = "运输时间")
	private String shippingTime;
	
}
