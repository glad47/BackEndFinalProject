package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价金属配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuotePremiumMetalDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "对应参数名称")
    private String name;
    @ApiModelProperty(value = "参数值")
    private BigDecimal metalPriceValue;
    @ApiModelProperty(value = "单位")
    private String unitName;
    @ApiModelProperty(value = "最小孔数区间值")
    private Integer minHolesValue;
    @ApiModelProperty(value = "最大孔数区间值")
    private Integer maxHolesValue;

}
