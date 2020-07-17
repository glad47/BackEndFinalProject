package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价金属配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("quote_premium_metal")
public class QuotePremiumMetalDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 对应参数名称
	 */
		private String name;
	/**
	 * 参数值
	 */
		private BigDecimal metalPriceValue;
	/**
	 * 单位
	 */
		private String unitName;
	/**
	 * 最小孔数区间值
	 */
		private Integer minHolesValue;
	/**
	 * 最大孔数区间值
	 */
		private Integer maxHolesValue;

}
