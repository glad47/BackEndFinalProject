package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 跟单员目标指标
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class BusinessTargetVO implements Serializable {
	private static final long serialVersionUID = 3474459590474670711L;

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 目标名称
	 */
	private String name;
		/**
	 * 目标指标
	 */
	private Integer value;
		/**
	 * 时间类型（1月目标 2年目标）
	 */
	private Integer dateType;
		/**
	 * 目标类型(1月款数 2销售额)
	 */
	private Integer type;
		/**
	 * 跟单员id
	 */
	private Integer businessId;
		/**
	 * 创建时间
	 */
	private Date gmtCreate;
		/**
	 * 跟单员名字
	 */
	private String businessName;
	
}
