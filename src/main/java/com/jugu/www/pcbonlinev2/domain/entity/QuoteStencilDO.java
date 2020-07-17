package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 钢网报价表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("quote_stencil")
public class QuoteStencilDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 规格x
	 */
		private Integer stencilSizex;
	/**
	 * 
	 */
		private Integer stencilSizey;
	/**
	 * 面积x
	 */
		private Integer stencilAreax;
	/**
	 * 
	 */
		private Integer stencilAreay;
	/**
	 * 单价
	 */
		private BigDecimal price;
	/**
	 * 重量
	 */
		private BigDecimal weight;
	/**
	 * 物料名
	 */
		private String materialName;

}
