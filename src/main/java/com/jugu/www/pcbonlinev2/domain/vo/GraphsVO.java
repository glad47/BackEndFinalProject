package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 跟单员统计表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class GraphsVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Long id;
		/**
	 * 跟单员id
	 */
	private Integer bid;
		/**
	 * 时间标识(201901)
	 */
	private String timeStr;
		/**
	 * 款数
	 */
	private Integer orderNumber;
		/**
	 * 订单销售额
	 */
	private BigDecimal orderSales;
	
}
