package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 切片订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Data
public class AssemblyQueryDTO implements Serializable {
    private static final long serialVersionUID = 4643566434253894856L;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "状态list")
    private List statusList;

}
