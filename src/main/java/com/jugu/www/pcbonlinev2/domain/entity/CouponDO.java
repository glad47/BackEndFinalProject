package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
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
    private static final long serialVersionUID = -3360401817693899171L;
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
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    /**
     * 劵状态
     */
    private Integer couponStatus;

}
