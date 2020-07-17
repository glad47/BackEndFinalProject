package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 快递国家分区表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class CourierCountryPartitionVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 快递公司id
	 */
	private Integer courierId;
		/**
	 * 国家id
	 */
	private String countryIds;
		/**
	 * 运输时间
	 */
	private String shippingTime;
	
}
