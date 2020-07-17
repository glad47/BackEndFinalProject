package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 城市表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class CityVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * 
	 */
	private Integer id;
		/**
	 * 城市所在的国家对应的id
	 */
	private Integer countryId;
		/**
	 * 省或者州的英文名称,若某国家没有这一个行政级别,则为空
	 */
	private String state;
		/**
	 * 城市的标准英文名称
	 */
	private String name;
		/**
	 * 城市的小写英文名称,用于搜索
	 */
	private String lowerName;
		/**
	 * 省或者州的中文名称,若某国家没有这一个行政级别
	 */
	private String cnState;
		/**
	 * 城市的标准中文名称
	 */
	private String cnName;
		/**
	 * 城市的代码(一般表示缩写
	 */
	private String cityCode;
		/**
	 * 省或者州代码(一般表示缩写),若某个国家没有州或省这个行政级别，则为空
	 */
	private String stateCode;
	
}
