package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价重量配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteWeightDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "厚度名称")
    private String thicknaesName;
    @ApiModelProperty(value = "FR41层重量")
    private BigDecimal fr41;
    @ApiModelProperty(value = "FR4 2层重量")
    private BigDecimal fr42;
    @ApiModelProperty(value = "FR4 4层重量")
    private BigDecimal fr44;
    @ApiModelProperty(value = "FR4 6层重量")
    private BigDecimal fr46;
    @ApiModelProperty(value = "FR4 8层重量")
    private BigDecimal fr48;
    @ApiModelProperty(value = "AL1层重量")
    private BigDecimal al1;
    @ApiModelProperty(value = "AL2层重量")
    private BigDecimal al2;
    @ApiModelProperty(value = "FR4+AL重量")
    private BigDecimal fr4al;
    @ApiModelProperty(value = "FPC重量")
    private BigDecimal fpc;

}
