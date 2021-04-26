package com.jugu.www.pcbonlinev2.domain.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "统一返回分页结果实体")
public  class PageResult<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 5074000774435553698L;

    /**
     * 当前页号
     */
    @ApiModelProperty(value = "当前页号")
    private Integer pageNo; //当前页号

    /**
     * 每页行数
     */
    @ApiModelProperty(value = "每页行数")
    private Integer pageSize; //每页行数

    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long total; //总记录数

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Long pageNum; //总页数

    /**
     * 动态的内容
     */
    @ApiModelProperty(value = "返回数据")
    private T data; //返回数据

    public PageResult() {
    }

    public PageResult(Integer pageNo, Integer pageSize, Long total, Long pageNum, T data) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.total = total;
        this.pageNum = pageNum;
        this.data = data;
    }


    public PageResult(IPage iPage, T list) {
        this.pageNo = Math.toIntExact(iPage.getCurrent());
        this.pageSize = Math.toIntExact(iPage.getSize());
        this.pageNum = iPage.getPages();
        this.total = iPage.getTotal();
        this.data = list;
    }
}
