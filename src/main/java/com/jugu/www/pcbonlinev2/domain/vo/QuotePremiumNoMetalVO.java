package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价非金属配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuotePremiumNoMetalVO implements Serializable {
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
	 * 参数值
	 */
	private BigDecimal noMetalPriceValue;
		/**
	 * 单位
	 */
	private String unitName;
	
}
