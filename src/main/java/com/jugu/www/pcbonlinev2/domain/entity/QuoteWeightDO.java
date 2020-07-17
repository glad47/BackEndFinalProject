package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价重量配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("quote_weight")
public class QuoteWeightDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 厚度名称
	 */
		private String thicknaesName;
	/**
	 * FR41层重量
	 */
		private BigDecimal fr41;
	/**
	 * FR4 2层重量
	 */
		private BigDecimal fr42;
	/**
	 * FR4 4层重量
	 */
		private BigDecimal fr44;
	/**
	 * FR4 6层重量
	 */
		private BigDecimal fr46;
	/**
	 * FR4 8层重量
	 */
		private BigDecimal fr48;
	/**
	 * AL1层重量
	 */
		private BigDecimal al1;
	/**
	 * AL2层重量
	 */
		private BigDecimal al2;
	/**
	 * FR4+AL重量
	 */
		private BigDecimal fr4al;
	/**
	 * FPC重量
	 */
		private BigDecimal fpc;

}
