package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 优惠券表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class CouponDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "优惠券id")
	private String couponCode;
		@ApiModelProperty(value = "优惠券类型")
	private Integer couponType;
		@ApiModelProperty(value = "优惠券金额")
	private Integer couponMoney;
		@ApiModelProperty(value = "有效开始时间")
	private Date startTime;
		@ApiModelProperty(value = "有效结束时间")
	private Date endTime;
		@ApiModelProperty(value = "用户id")
	private Integer userId;
		@ApiModelProperty(value = "创建时间")
	private Date gmtCreate;
		@ApiModelProperty(value = "修改时间")
	private Date gmtModified;
		@ApiModelProperty(value = "劵状态")
	private Integer couponStatus;
	
}
