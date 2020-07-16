package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Data
@TableName("article")
public class ArticleDO implements Serializable {
	private static final long serialVersionUID = -131314776416315833L;
	/**
	 * id
	 */
		@TableId
		private Long id;
	/**
	 * 文章名称
	 */
		private String articleName;
	/**
	 * 发布时间
	 */
		private Date articleTime;
	/**
	 * 发布ip
	 */
		private String articleIp;
	/**
	 * 查看人数
	 */
		private Integer articleClick;
	/**
	 * 点赞数
	 */
		private Integer articleLike;
	/**
	 * 评论数
	 */
		private Integer articleComment;
	/**
	 * 文章内容
	 */
		private String articleContent;
	/**
	 * 文章分类
	 */
		private String articleClassify;
	/**
	 * 文章类型（1 博客 2 反馈）
	 */
		private Integer articleType;
	/**
	 * 文章发布用户
	 */
		private Integer articleUserId;
	/**
	 * 文章状态
	 */
		private Integer articleStatus;
	/**
	 * 文章发布用户名
	 */
		private String articleUserName;
	/**
	 * 文章评价星级
	 */
		private Integer articleScore;
	/**
	 * 主题
	 */
		private String articleTitle;
	/**
	 * 关键字
	 */
		private String articleKeyword;
	/**
	 * 描述
	 */
		private String articleDescribe;
	/**
	 * 是否置顶(默认0)
	 */
		private Integer articleKing;
	/**
	 * 更新时间
	 */
		private Date articleUpdateTime;
	/**
	 * 文章内链
	 */
		private String articleInternalChain;

}
