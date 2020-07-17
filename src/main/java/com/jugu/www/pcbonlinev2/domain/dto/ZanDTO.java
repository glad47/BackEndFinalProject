package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 点赞
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class ZanDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "id")
	private Integer id;
		@ApiModelProperty(value = "对应作品或评论的id")
	private Integer typeId;
		@ApiModelProperty(value = "点赞类型 1 文章 2 评论")
	private Integer type;
		@ApiModelProperty(value = "用户id")
	private Integer userId;
		@ApiModelProperty(value = "点赞状态 0 取消 1 有效")
	private Integer status;
	
}
