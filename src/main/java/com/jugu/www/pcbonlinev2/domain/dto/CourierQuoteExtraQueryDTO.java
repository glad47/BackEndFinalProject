package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 运费其它项表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "运费其它项表")
public class CourierQuoteExtraQueryDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "运费项名称")
    private String itemName;
    @ApiModelProperty(value = "运费项收费比")
    private BigDecimal itemRate;

}
