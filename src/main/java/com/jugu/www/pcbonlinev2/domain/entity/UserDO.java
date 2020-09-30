package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * 用户表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
@TableName("user")
public class UserDO implements Serializable {
    private static final long serialVersionUID = -4858992154661509704L;
    /**
     * 用户id
     */
    @TableId
    private Integer id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * skypeid
     */
    private String skypeId;
    /**
     * googleid
     */
    private String googleId;
    /**
     * facebookid
     */
    private String facebookId;
    /**
     * 手机
     */
    private String mobilePhone;
    /**
     * 地址
     */
    private String address;
    /**
     * 国家
     */
    private String country;
    /**
     * 城市
     */
    private String city;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 工作角色
     */
    private String jobrole;
    /**
     * 应用
     */
    private String applications;
    /**
     * 是否失效（1失效0没有）
     */
    private Integer invalidMark;
    /**
     * 头像
     */
    private String favicon;
    /**
     * 注册时间
     */
    private Date gmtCreate;
    /**
     * 更新时间
     */
    private Date gmtModified;
    /**
     * 逻辑主键
     */
    private String uuid;
    /**
     * 用户系统id
     */
    private String userSystemId;
    /**
     * 业务员id
     */
    private Integer businessId;
    /**
     * 订单数量编号
     */
    private Integer orderNumNo;
    /**
     * facebook登录id
     */
    private String facebookLoginId;
    /**
     * google登录id
     */
    private String googleLoginId;
    /**
     * 邀请人id
     */
    private Integer invitationUserId;
    /**
     * 积分
     */
    private Integer integral;
    /**
     * 用户注册ip
     */
    private String userIp;
    /**
     * 用户类型（0客户系统用户 1 内部系统用户）
     */
    private Integer userType;
    /**
     * 公司名称
     */
    private String companName;
    /**
     * 是否需要审核标识(0默认需要 1不需要)
     */
    private Integer auditMark;
    /**
     * 邮编
     */
    private String postcode;
    /**
     * 付款方式
     */
    private String paymentType;
    /**
     * 发货方式
     */
    private String deliveryType;
    /**
     * 联系人
     */
    private String contact;
    /**
     * 用户指定快递公司
     */
    private String userCourierName;
    /**
     * 用户指定快递公司账号
     */
    private String userCourierAccount;
    /**
     * 包装要求
     */
    private String packageRequirements;
    /**
     * 出货报告
     */
    private String deliveryReport;
    /**
     * 客户标准
     */
    private String customerStandards;
    /**
     * 生产确认
     */
    private Integer productionVerification;
    /**
     * 网站
     */
    private String siteUrl;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 提成
     */
    private String commission;
    /**
     * 第一次支付时间
     */
    private Date firstPayTime;
    /**
     * 对应跟单员名字
     */
    private String businessName;
    /**
     * 状态（0未审核  1需审核 2已审核）
     */
    private Integer status;
    /**
     * 是否来自公司分配（0默认否 1 是）
     */
    private Integer isSourceCompany;
    /**
     * 用户充值的点数
     */
    private BigDecimal points;

}
