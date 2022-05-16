package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章评论表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class ArticleCommentDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "收到评论的用户id")
    private Integer userId;
    @ApiModelProperty(value = "评论文章id")
    private Long articleId;
    @ApiModelProperty(value = "评论id")
    private Integer commitId;
    @ApiModelProperty(value = "评论内容")
    private String commitContent;
    @ApiModelProperty(value = "评论用户id")
    private Integer commitUserId;
    @ApiModelProperty(value = "ip")
    private String commitIp;
    @ApiModelProperty(value = "评论时间")
    private Date commitTime;
    @ApiModelProperty(value = "评论点赞次数")
    private Integer zanNumber;

}
