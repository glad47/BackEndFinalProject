package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 跟单员统计表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "跟单员统计表")
public class GraphsQueryDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Long id;
		@ApiModelProperty(value = "跟单员id")
	private Integer bid;
		@ApiModelProperty(value = "时间标识(201901)")
	private String timeStr;
		@ApiModelProperty(value = "款数")
	private Integer orderNumber;
		@ApiModelProperty(value = "订单销售额")
	private BigDecimal orderSales;
	
}
