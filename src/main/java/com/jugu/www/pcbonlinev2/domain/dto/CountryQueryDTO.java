package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Data
@ApiModel(value = "国家")
public class CountryQueryDTO implements Serializable {
	private static final long serialVersionUID = -5549389145451972427L;

		@ApiModelProperty(value = "")
	private Integer id;
		@ApiModelProperty(value = "国家所在的七大洲对应的id,对应于表continent的主键")
	private Integer continentId;
		@ApiModelProperty(value = "国家英文常用标准名称，主要用于显示")
	private String name;
		@ApiModelProperty(value = "国家英文标准名称的小写，主要用于搜索与比较")
	private String lowerName;
		@ApiModelProperty(value = "国家英文代码,国家名称缩写")
	private String countryCode;
		@ApiModelProperty(value = "国家英文名称全称")
	private String fullName;
		@ApiModelProperty(value = "国家中文常用标准名称")
	private String cname;
		@ApiModelProperty(value = "国家中文全称名称，非缩写")
	private String fullCname;
		@ApiModelProperty(value = "备注，国家的一些说明")
	private String remark;
		@ApiModelProperty(value = "")
	private String fbCountryCode;
		@ApiModelProperty(value = "")
	private String lang;
	
}
