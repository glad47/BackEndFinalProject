package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价加工时间配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteUrgentBuildDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "平米id")
	private Integer areaId;
		@ApiModelProperty(value = "加急类型id")
	private Integer urgentTypeId;
		@ApiModelProperty(value = "层数id")
	private Integer layerNumberId;
		@ApiModelProperty(value = "建造天数")
	private String dayNumber;
		@ApiModelProperty(value = "费用")
	private BigDecimal price;
	
}
