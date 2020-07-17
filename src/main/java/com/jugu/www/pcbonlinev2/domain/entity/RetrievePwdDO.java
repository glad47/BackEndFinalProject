package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 取回密码
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:38
 */
@Data
@TableName("retrieve_pwd")
public class RetrievePwdDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * 
	 */
		@TableId
		private Integer id;
	/**
	 * 
	 */
		private Integer userId;
	/**
	 * 
	 */
		private String expirationTiem;
	/**
	 * 
	 */
		private String randomMd5;

}
