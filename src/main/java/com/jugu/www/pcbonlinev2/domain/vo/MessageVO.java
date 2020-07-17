package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 信息消息
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class MessageVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 发送人
	 */
	private String sendUser;
		/**
	 * 接收人
	 */
	private String receiveUser;
		/**
	 * 群id
	 */
	private String groupId;
		/**
	 * 是否已读（0未读 1 已读）
	 */
	private Integer isread;
		/**
	 * 类型（0 单聊 1 群聊）
	 */
	private Integer type;
		/**
	 * 消息内容
	 */
	private String content;
		/**
	 * 创建用户
	 */
	private Integer createUser;
		/**
	 * 修改时间
	 */
	private Date gmtModified;
		/**
	 * 创建时间
	 */
	private Date gmtCreate;
	
}
