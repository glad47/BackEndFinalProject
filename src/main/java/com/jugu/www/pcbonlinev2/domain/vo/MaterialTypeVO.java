package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 材料型号表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class MaterialTypeVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 材料id
	 */
	private Integer materialId;
		/**
	 * 材料name
	 */
	private String materialName;
		/**
	 * 材料型号
	 */
	private String materialType;
	
}
