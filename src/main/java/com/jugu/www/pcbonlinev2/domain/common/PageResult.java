package com.jugu.www.pcbonlinev2.domain.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(
        value = "统一返回分页结果实体",
        description = "封装统一返回分页结果信息实体"
)
public  class PageResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5074000774435553698L;

    /**
     * 当前页号
     */
    @ApiModelProperty(value = "当前页号")
    private Integer pageNo;

    /**
     * 每页行数
     */
    @ApiModelProperty(value = "每页行数")
    private Integer pageSize;

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long total;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Long pageNum;

    /**
     * 动态的内容
     */
    @ApiModelProperty(value = "返回数据")
    private T data;
}
