package com.jugu.www.pcbonlinev2.domain.vo;

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
public class QuotePcbtypeVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * 
	 */
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
