package com.jugu.www.pcbonlinev2.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 8040135336637200184L;

    @ApiModelProperty(value = "用户id")
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String userName;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "skypeid")
    private String skypeId;
    @ApiModelProperty(value = "googleid")
    private String googleId;
    @ApiModelProperty(value = "facebookid")
    private String facebookId;
    @ApiModelProperty(value = "手机")
    private String mobilePhone;
    @ApiModelProperty(value = "地址")
    private String address;
    @ApiModelProperty(value = "国家")
    private String country;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "业务类型")
    private String businessType;
    @ApiModelProperty(value = "工作角色")
    private String jobrole;
    @ApiModelProperty(value = "应用")
    private String applications;
    @ApiModelProperty(value = "是否失效（1失效0没有）")
    private Integer invalidMark;
    @ApiModelProperty(value = "头像")
    private String favicon;
    @ApiModelProperty(value = "注册时间")
    private LocalDateTime gmtCreate;
    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;
    @ApiModelProperty(value = "逻辑主键")
    private String uuid;
    @ApiModelProperty(value = "用户系统id")
    private String userSystemId;
    @ApiModelProperty(value = "业务员id")
    private Integer businessId;
    @ApiModelProperty(value = "订单数量编号")
    private Integer orderNumNo;
    @ApiModelProperty(value = "facebook登录id")
    private String facebookLoginId;
    @ApiModelProperty(value = "google登录id")
    private String googleLoginId;
    @ApiModelProperty(value = "邀请人id")
    private Integer invitationUserId;
    @ApiModelProperty(value = "积分")
    private Integer integral;
    @ApiModelProperty(value = "用户注册ip")
    private String userIp;
    @ApiModelProperty(value = "用户类型（0客户系统用户 1 内部系统用户）")
    private Integer userType;
    @ApiModelProperty(value = "公司名称")
    private String companName;
    @ApiModelProperty(value = "是否需要审核标识(0默认需要 1不需要)")
    private Integer auditMark;
    @ApiModelProperty(value = "邮编")
    private String postcode;
    @ApiModelProperty(value = "付款方式")
    private String paymentType;
    @ApiModelProperty(value = "发货方式")
    private String deliveryType;
    @ApiModelProperty(value = "联系人")
    private String contact;
    @ApiModelProperty(value = "用户指定快递公司")
    private String userCourierName;
    @ApiModelProperty(value = "用户指定快递公司账号")
    private String userCourierAccount;
    @ApiModelProperty(value = "包装要求")
    private String packageRequirements;
    @ApiModelProperty(value = "出货报告")
    private String deliveryReport;
    @ApiModelProperty(value = "客户标准")
    private String customerStandards;
    @ApiModelProperty(value = "生产确认")
    private Integer productionVerification;
    @ApiModelProperty(value = "网站")
    private String siteUrl;
    @ApiModelProperty(value = "渠道")
    private String channel;
    @ApiModelProperty(value = "提成")
    private String commission;
    @ApiModelProperty(value = "第一次支付时间")
    private LocalDateTime firstPayTime;
    @ApiModelProperty(value = "对应跟单员名字")
    private String businessName;
    @ApiModelProperty(value = "状态（0未审核  1需审核 2已审核）")
    private Integer status;
    @ApiModelProperty(value = "是否来自公司分配（0默认否 1 是）")
    private Integer isSourceCompany;
}
