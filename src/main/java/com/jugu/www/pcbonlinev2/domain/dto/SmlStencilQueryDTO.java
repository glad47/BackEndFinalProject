package com.jugu.www.pcbonlinev2.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 钢网订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@Data
public class SmlStencilQueryDTO implements Serializable {
    private static final long serialVersionUID = -6562070232279793927L;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态list")
    private List statusList;

}
