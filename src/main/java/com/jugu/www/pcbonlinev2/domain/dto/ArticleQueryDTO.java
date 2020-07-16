package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 文章表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Data
@ApiModel(value = "文章表")
public class ArticleQueryDTO implements Serializable {
	private static final long serialVersionUID = -8740260543793968296L;

		@ApiModelProperty(value = "id")
	private Long id;
		@ApiModelProperty(value = "文章名称")
	private String articleName;
		@ApiModelProperty(value = "发布时间")
	private Date articleTime;
		@ApiModelProperty(value = "发布ip")
	private String articleIp;
		@ApiModelProperty(value = "查看人数")
	private Integer articleClick;
		@ApiModelProperty(value = "点赞数")
	private Integer articleLike;
		@ApiModelProperty(value = "评论数")
	private Integer articleComment;
		@ApiModelProperty(value = "文章内容")
	private String articleContent;
		@ApiModelProperty(value = "文章分类")
	private String articleClassify;
		@ApiModelProperty(value = "文章类型（1 博客 2 反馈）")
	private Integer articleType;
		@ApiModelProperty(value = "文章发布用户")
	private Integer articleUserId;
		@ApiModelProperty(value = "文章状态")
	private Integer articleStatus;
		@ApiModelProperty(value = "文章发布用户名")
	private String articleUserName;
		@ApiModelProperty(value = "文章评价星级")
	private Integer articleScore;
		@ApiModelProperty(value = "主题")
	private String articleTitle;
		@ApiModelProperty(value = "关键字")
	private String articleKeyword;
		@ApiModelProperty(value = "描述")
	private String articleDescribe;
		@ApiModelProperty(value = "是否置顶(默认0)")
	private Integer articleKing;
		@ApiModelProperty(value = "更新时间")
	private Date articleUpdateTime;
		@ApiModelProperty(value = "文章内链")
	private String articleInternalChain;
	
}
