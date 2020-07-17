package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价参数值配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("quote_parameter_value")
public class QuoteParameterValueDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * quote_parameter表ID
	 */
		private Integer parameterId;
	/**
	 * 参数值
	 */
		private String parameterValue;
	/**
	 * 是否删除（1代表删除，0代表未删除）
	 */
		private Integer isDelete;

}
