package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
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
public class CouponQueryDTO implements Serializable {
    private static final long serialVersionUID = 3116175757865030411L;

    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "劵状态")
    private Integer couponStatus;

}
