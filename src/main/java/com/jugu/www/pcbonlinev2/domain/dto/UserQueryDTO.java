package com.jugu.www.pcbonlinev2.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "用户查询封装实体",description = "用户查询封装实体")
public class UserQueryDTO implements Serializable {

    private static final long serialVersionUID = 7501174233045276429L;

    @ApiModelProperty(value = "用户名",required = false)
    private String username;
}
