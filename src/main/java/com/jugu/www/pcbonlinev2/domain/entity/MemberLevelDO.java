package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

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
@TableName("member_level")
public class MemberLevelDO implements Serializable {
    private static final long serialVersionUID = -7461661186207534897L;
    /**
     * id
     */
    @TableId
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
