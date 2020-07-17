package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价其他配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuotePremiumOtherVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 对应参数名称
	 */
	private String name;
		/**
	 * 其他参数值
	 */
	private BigDecimal otherPriceValue;
		/**
	 * 单位
	 */
	private String unitName;
	
}
