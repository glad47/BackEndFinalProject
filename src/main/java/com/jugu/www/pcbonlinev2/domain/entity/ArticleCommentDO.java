package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 文章评论表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("article_comment")
public class ArticleCommentDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 收到评论的用户id
	 */
		private Integer userId;
	/**
	 * 评论文章id
	 */
		private Long articleId;
	/**
	 * 评论id
	 */
		private Integer commitId;
	/**
	 * 评论内容
	 */
		private String commitContent;
	/**
	 * 评论用户id
	 */
		private Integer commitUserId;
	/**
	 * ip
	 */
		private String commitIp;
	/**
	 * 评论时间
	 */
		private Date commitTime;
	/**
	 * 评论点赞次数
	 */
		private Integer zanNumber;

}
