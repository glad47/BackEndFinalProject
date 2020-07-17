package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 取回密码
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:38
 */
@Data
@ApiModel(value = "取回密码")
public class RetrievePwdQueryDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "")
	private Integer id;
		@ApiModelProperty(value = "")
	private Integer userId;
		@ApiModelProperty(value = "")
	private String expirationTiem;
		@ApiModelProperty(value = "")
	private String randomMd5;
	
}
