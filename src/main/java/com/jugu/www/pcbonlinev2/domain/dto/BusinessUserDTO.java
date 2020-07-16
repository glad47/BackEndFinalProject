package com.jugu.www.pcbonlinev2.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BusinessUserDTO implements Serializable {
    private static final long serialVersionUID = -5987635509419292087L;

    @ApiModelProperty(value = "主键id")
    private Integer id;
    @ApiModelProperty(value = "跟单员name")
    private String name;
    @ApiModelProperty(value = "跟单员邮箱")
    private String contactEmail;
    @ApiModelProperty(value = "跟单员电话")
    private String telephone;
    @ApiModelProperty(value = "跟单员标识")
    private String prefixNo;
}
