package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 优惠劵生成规则表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("coupon_rule")
public class CouponRuleDO implements Serializable {
    private static final long serialVersionUID = -1724976609645294902L;
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 优惠券规则名称
     */
    @TableField(value = "`name`")
    private String name;
    /**
     * 规则描述
     */
    @TableField(value = "`describe`")
    private String describe;
    /**
     * 优惠券有效时间（天）
     */
    private Integer availableTime;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 劵序列号长度（默认为9）
     */
    private String codeLen;
    /**
     * 券标识码
     */
    private String codeFlag;
    /**
     * 劵标识长度
     */
    private String codeFlagBitLen;
    /**
     * 劵验证为长度
     */
    private String codeCheckBitLen;
    /**
     * 券数量
     */
    private Integer codeNmber;
    /**
     * 创建人id
     */
    private Integer createUser;
    /**
     * 金额
     */
    private Integer money;

}
