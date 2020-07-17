package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 钢网报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteStencilDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "规格x")
	private Integer stencilSizex;
		@ApiModelProperty(value = "")
	private Integer stencilSizey;
		@ApiModelProperty(value = "面积x")
	private Integer stencilAreax;
		@ApiModelProperty(value = "")
	private Integer stencilAreay;
		@ApiModelProperty(value = "单价")
	private BigDecimal price;
		@ApiModelProperty(value = "重量")
	private BigDecimal weight;
		@ApiModelProperty(value = "物料名")
	private String materialName;
	
}
