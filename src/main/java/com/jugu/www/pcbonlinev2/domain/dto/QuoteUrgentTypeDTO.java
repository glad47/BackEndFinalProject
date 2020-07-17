package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价加急类型配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteUrgentTypeDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "加急类型")
	private String urgentType;
		@ApiModelProperty(value = "是否删除（1代表删除，0代表未删除）")
	private Integer isDelete;
	
}
