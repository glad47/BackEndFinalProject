package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价pcb基材类型配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("quote_pcbtype")
public class QuotePcbtypeDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * 
	 */
		@TableId
		private Integer id;
	/**
	 * pcb类型名称
	 */
		private String pcbTypeName;
	/**
	 * 是否删除（1表示是，0表示否）
	 */
		private Integer isDelete;

}
