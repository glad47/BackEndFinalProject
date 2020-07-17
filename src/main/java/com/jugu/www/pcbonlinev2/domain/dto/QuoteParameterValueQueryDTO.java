package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价参数值配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "报价参数值配置表")
public class QuoteParameterValueQueryDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "quote_parameter表ID")
	private Integer parameterId;
		@ApiModelProperty(value = "参数值")
	private String parameterValue;
		@ApiModelProperty(value = "是否删除（1代表删除，0代表未删除）")
	private Integer isDelete;
	
}
