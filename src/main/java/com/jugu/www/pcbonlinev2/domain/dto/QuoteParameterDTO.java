package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价参数名配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteParameterDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "参数类型id")
    private Integer parameterTypeId;
    @ApiModelProperty(value = "参数名称")
    private String parameterName;
    @ApiModelProperty(value = "参数字段对应名称")
    private String parameterField;
    @ApiModelProperty(value = "是否删除（1表示删除，0表示未删除）")
    private Integer isDelete;

}
