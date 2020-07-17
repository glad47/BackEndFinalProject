package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("courier_quote_extra")
public class CourierQuoteExtraDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
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
