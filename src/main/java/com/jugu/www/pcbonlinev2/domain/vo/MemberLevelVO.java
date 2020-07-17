package com.jugu.www.pcbonlinev2.domain.vo;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 会员等级表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class MemberLevelVO implements Serializable {
	// TODO serialVersionUid

		/**
	 * id
	 */
	private Integer id;
		/**
	 * 等级
	 */
	private String levelMember;
		/**
	 * 点数范围
	 */
	private Integer minPoint;
		/**
	 * 点数范围
	 */
	private Integer maxPoint;
		/**
	 * 优惠值(1整数)
	 */
	private Integer preferentialDetail;
		/**
	 * 优惠百分比
	 */
	private String membersStr;
	
}
