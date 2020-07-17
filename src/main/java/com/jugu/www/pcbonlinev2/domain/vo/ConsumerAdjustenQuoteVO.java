package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 客户调整价格报价表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class ConsumerAdjustenQuoteVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 报价配置id
	 */
	private Integer quoteConfigId;
		/**
	 * 客户id
	 */
	private Integer userId;
		/**
	 * 调整价
	 */
	private BigDecimal adjustedQuote;
	
}
