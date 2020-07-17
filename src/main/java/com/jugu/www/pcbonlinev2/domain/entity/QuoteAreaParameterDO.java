package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价参数面积配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("quote_area_parameter")
public class QuoteAreaParameterDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 面积尺寸名称
	 */
		private String areaParameterName;
	/**
	 * 最小面积参数
	 */
		private BigDecimal areaMinParameter;
	/**
	 * 最大面积参数
	 */
		private BigDecimal areaMaxParameter;
	/**
	 * 是否删除（1表示是，0表示否）
	 */
		private Integer isDelete;

}
