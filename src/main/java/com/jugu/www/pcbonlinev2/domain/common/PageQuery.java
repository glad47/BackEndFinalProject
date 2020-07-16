package com.jugu.www.pcbonlinev2.domain.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
//@ApiModel(
//        value = "统一查询实体"
////        description = "封装统一查询信息实体"
//)
public class PageQuery<T,E> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5443997546397888159L;

    /**
     * 当前页
     */
    @NotNull(message = "页号不能为空！")
    @Min(value = 1, message = "页号必须为正数！")
//    @ApiModelProperty(
//            name = "pageNo",
//            value = "当前页",
//            required = true,
//            dataType = "int"
//    )
    private Integer pageNo = 1;

    /**
     * 每页条数
     */
    @NotNull(message = "每页条数不能为空！")
    @Max(value = 100, message = "每页条数不能超过100条！")
//    @ApiModelProperty(
//            value = "每页条数",
//            required = true,
//            dataType = "int"
//    )
    private Integer pageSize = 20;

    /**
     * 动态查询条件
     */
    @Valid
    @NotNull(message = "动态查询条件不能为空！")
//    @ApiModelProperty(
//            value = "查询条件"
//    )
    private T query;

//    @ApiModelProperty(
//            value = "mybatis-plus分页参数",
//            hidden = true
//    )
    private Page<E> page;

    public PageQuery(@NotNull(message = "页号不能为空！") @Min(value = 1, message = "页号必须为正数！") Integer pageNo, @NotNull(message = "每页条数不能为空！") @Max(value = 100, message = "每页条数不能超过100条！") Integer pageSize, @Valid @NotNull(message = "动态查询条件不能为空！") T query) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.query = query;

        //mybatis-plus分页
        this.page = new Page<>(pageNo,pageSize);
    }

    public PageQuery() {
    }
}
