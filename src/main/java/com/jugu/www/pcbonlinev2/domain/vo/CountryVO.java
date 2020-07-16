package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Data
public class CountryVO implements Serializable {
	private static final long serialVersionUID = -3695701545845705395L;

		/**
	 * 
	 */
	private Integer id;
		/**
	 * 国家所在的七大洲对应的id,对应于表continent的主键
	 */
	private Integer continentId;
		/**
	 * 国家英文常用标准名称，主要用于显示
	 */
	private String name;
		/**
	 * 国家英文标准名称的小写，主要用于搜索与比较
	 */
	private String lowerName;
		/**
	 * 国家英文代码,国家名称缩写
	 */
	private String countryCode;
		/**
	 * 国家英文名称全称
	 */
	private String fullName;
		/**
	 * 国家中文常用标准名称
	 */
	private String cname;
		/**
	 * 国家中文全称名称，非缩写
	 */
	private String fullCname;
		/**
	 * 备注，国家的一些说明
	 */
	private String remark;
		/**
	 * 
	 */
	private String fbCountryCode;
		/**
	 * 
	 */
	private String lang;
	
}
