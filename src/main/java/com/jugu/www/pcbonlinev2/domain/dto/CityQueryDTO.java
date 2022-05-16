package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 城市表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@ApiModel(value = "城市表")
public class CityQueryDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "")
    private Integer id;
    @ApiModelProperty(value = "城市所在的国家对应的id")
    private Integer countryId;
    @ApiModelProperty(value = "省或者州的英文名称,若某国家没有这一个行政级别,则为空")
    private String state;
    @ApiModelProperty(value = "城市的标准英文名称")
    private String name;
    @ApiModelProperty(value = "城市的小写英文名称,用于搜索")
    private String lowerName;
    @ApiModelProperty(value = "省或者州的中文名称,若某国家没有这一个行政级别")
    private String cnState;
    @ApiModelProperty(value = "城市的标准中文名称")
    private String cnName;
    @ApiModelProperty(value = "城市的代码(一般表示缩写")
    private String cityCode;
    @ApiModelProperty(value = "省或者州代码(一般表示缩写),若某个国家没有州或省这个行政级别，则为空")
    private String stateCode;

}
