package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 材料表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class MaterialDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "材料名")
	private String name;
	
}
