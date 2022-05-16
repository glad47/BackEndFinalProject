package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价非金属配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuotePremiumNoMetalDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "对应参数名称")
    private String name;
    @ApiModelProperty(value = "参数值")
    private BigDecimal noMetalPriceValue;
    @ApiModelProperty(value = "单位")
    private String unitName;

}
