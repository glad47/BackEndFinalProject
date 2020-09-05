package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 重量公式表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class WeightConfigDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "PCB类型")
    private Integer pcbtype;
    @ApiModelProperty(value = "层数")
    private Integer layerNum;
    @ApiModelProperty(value = "成品厚度")
    private Integer finishThickness;
    @ApiModelProperty(value = "内层铜厚")
    private Integer innerLayerCopper;
    @ApiModelProperty(value = "外层铜厚")
    private Integer outerLayerCopper;
    @ApiModelProperty(value = "重量")
    private BigDecimal weight;

}
