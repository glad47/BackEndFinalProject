package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价参数类型配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteParameterTypeVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 *  参数类型名称
	 */
	private String parameterTypeName;
		/**
	 * 是否删除（1表示删除，0表示未删除）
	 */
	private Integer isDelete;
	
}
