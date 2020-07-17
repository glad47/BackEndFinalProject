package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价参数名配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("quote_parameter")
public class QuoteParameterDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 参数类型id
	 */
		private Integer parameterTypeId;
	/**
	 * 参数名称
	 */
		private String parameterName;
	/**
	 * 参数字段对应名称
	 */
		private String parameterField;
	/**
	 * 是否删除（1表示删除，0表示未删除）
	 */
		private Integer isDelete;

}
