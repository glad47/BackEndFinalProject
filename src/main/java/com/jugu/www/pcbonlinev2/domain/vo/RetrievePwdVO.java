package com.jugu.www.pcbonlinev2.domain.vo;

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
public class RetrievePwdVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * 
	 */
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
