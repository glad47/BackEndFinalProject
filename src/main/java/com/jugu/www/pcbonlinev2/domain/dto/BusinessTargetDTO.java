package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 跟单员目标指标
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class BusinessTargetDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "目标名称")
    private String name;
    @ApiModelProperty(value = "目标指标")
    private Integer value;
    @ApiModelProperty(value = "时间类型（1月目标 2年目标）")
    private Integer dateType;
    @ApiModelProperty(value = "目标类型(1月款数 2销售额)")
    private Integer type;
    @ApiModelProperty(value = "跟单员id")
    private Integer businessId;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "跟单员名字")
    private String businessName;

}
