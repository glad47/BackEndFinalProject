package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 生产日志
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-29 15:45:00
 */
@Data
public class ProductionLogDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "产品id")
    private Integer productId;
    @ApiModelProperty(value = "流程名")
    private String processName;
    @ApiModelProperty(value = "流程时间")
    private Date processTime;
    @ApiModelProperty(value = "产品类型(1pcb,2钢网,3贴片)")
    private Integer processType;

}
