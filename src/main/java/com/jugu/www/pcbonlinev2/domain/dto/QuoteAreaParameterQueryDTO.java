package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价参数面积配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "报价参数面积配置表")
public class QuoteAreaParameterQueryDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "面积尺寸名称")
	private String areaParameterName;
		@ApiModelProperty(value = "最小面积参数")
	private BigDecimal areaMinParameter;
		@ApiModelProperty(value = "最大面积参数")
	private BigDecimal areaMaxParameter;
		@ApiModelProperty(value = "是否删除（1表示是，0表示否）")
	private Integer isDelete;
	
}
