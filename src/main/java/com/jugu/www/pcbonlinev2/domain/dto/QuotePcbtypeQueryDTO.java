package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价pcb基材类型配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "报价pcb基材类型配置表")
public class QuotePcbtypeQueryDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "")
    private Integer id;
    @ApiModelProperty(value = "pcb类型名称")
    private String pcbTypeName;
    @ApiModelProperty(value = "是否删除（1表示是，0表示否）")
    private Integer isDelete;

}
