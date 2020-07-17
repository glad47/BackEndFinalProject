package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 客户调整价格报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class ConsumerAdjustenQuoteDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "报价配置id")
	private Integer quoteConfigId;
		@ApiModelProperty(value = "客户id")
	private Integer userId;
		@ApiModelProperty(value = "调整价")
	private BigDecimal adjustedQuote;
	
}
