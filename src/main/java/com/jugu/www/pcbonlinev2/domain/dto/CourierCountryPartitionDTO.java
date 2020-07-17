package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 快递国家分区表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class CourierCountryPartitionDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "快递公司id")
	private Integer courierId;
		@ApiModelProperty(value = "国家id")
	private String countryIds;
		@ApiModelProperty(value = "运输时间")
	private String shippingTime;
	
}
