package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 优惠券表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("coupon")
public class CouponDO implements Serializable {
	// TODO serialVersionUid
	/**
	 * id
	 */
		@TableId
		private Integer id;
	/**
	 * 优惠券id
	 */
		private String couponCode;
	/**
	 * 优惠券类型
	 */
		private Integer couponType;
	/**
	 * 优惠券金额
	 */
		private Integer couponMoney;
	/**
	 * 有效开始时间
	 */
		private Date startTime;
	/**
	 * 有效结束时间
	 */
		private Date endTime;
	/**
	 * 用户id
	 */
		private Integer userId;
	/**
	 * 创建时间
	 */
		private Date gmtCreate;
	/**
	 * 修改时间
	 */
		private Date gmtModified;
	/**
	 * 劵状态
	 */
		private Integer couponStatus;

}
