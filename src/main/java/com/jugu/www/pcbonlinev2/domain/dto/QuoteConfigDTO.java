package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价公式表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 17:47:29
 */
@Data
public class QuoteConfigDTO implements Serializable {
    private static final long serialVersionUID = -4227274842201430814L;

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "面积 id")
    private Integer areaSq;
    @ApiModelProperty(value = "基材对应pcbtypeid")
    private Integer pcbtype;
    @ApiModelProperty(value = "层数id")
    private Integer layerNum;
    @ApiModelProperty(value = "加急类型id")
    private Integer urgentType;
    @ApiModelProperty(value = "参数类型id")
    private Integer parameterType;
    @ApiModelProperty(value = "参数名称id")
    private Integer parameterName;
    @ApiModelProperty(value = "判断条件")
    private String quoteCondition;
    @ApiModelProperty(value = "参数值id")
    private Integer parameter;
    @ApiModelProperty(value = "加价类别id")
    private Integer premiumType;
    @ApiModelProperty(value = "报价")
    private BigDecimal quote;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
    @ApiModelProperty(value = "是否删除（1表示是，0表示否）")
    private Integer isDelete;

}
