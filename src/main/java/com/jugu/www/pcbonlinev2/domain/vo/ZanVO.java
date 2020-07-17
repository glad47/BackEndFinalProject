package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 点赞
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class ZanVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 对应作品或评论的id
	 */
	private Integer typeId;
		/**
	 * 点赞类型 1 文章 2 评论
	 */
	private Integer type;
		/**
	 * 用户id
	 */
	private Integer userId;
		/**
	 * 点赞状态 0 取消 1 有效
	 */
	private Integer status;
	
}
