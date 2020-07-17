package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 报价加急类型配置表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteUrgentTypeVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 加急类型
	 */
	private String urgentType;
		/**
	 * 是否删除（1代表删除，0代表未删除）
	 */
	private Integer isDelete;
	
}
