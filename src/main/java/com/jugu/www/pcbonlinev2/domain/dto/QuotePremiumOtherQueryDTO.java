package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价其他配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "报价其他配置表")
public class QuotePremiumOtherQueryDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "对应参数名称")
	private String name;
		@ApiModelProperty(value = "其他参数值")
	private BigDecimal otherPriceValue;
		@ApiModelProperty(value = "单位")
	private String unitName;
	
}
