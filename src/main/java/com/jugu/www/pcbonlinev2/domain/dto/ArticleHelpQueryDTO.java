package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 帮助主题表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-17 10:29:18
 */
@Data
@ApiModel(value = "帮助主题表")
public class ArticleHelpQueryDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "主题")
    private String title;
    @ApiModelProperty(value = "一级分类 common problem,technical knowledge,coupon usage,quality related")
    private String classifyOne;
    @ApiModelProperty(value = "二级分类")
    private String classifyTwo;
    @ApiModelProperty(value = "内容")
    private String content;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

}
