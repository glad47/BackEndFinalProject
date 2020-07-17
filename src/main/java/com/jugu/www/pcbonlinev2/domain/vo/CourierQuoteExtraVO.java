package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 运费其它项表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class CourierQuoteExtraVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 运费项名称
	 */
	private String itemName;
		/**
	 * 运费项收费比
	 */
	private BigDecimal itemRate;
	
}
