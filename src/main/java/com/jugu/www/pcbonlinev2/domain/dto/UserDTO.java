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

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

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
    private Byte invalidMark;

    @ApiModelProperty(value = "头像")
    private String favicon;

    @ApiModelProperty(value = "注册时间")
    private LocalDateTime gmtCreate;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "逻辑主键")
    private String uuid;

    private Integer businessId;

    private String  businessName;

    private String businessFavicon;

    private String userSystemId;

    private Boolean remeberMe; //记住我

    private String facebookLoginId;

    private String googleLoginId;

    private Integer invitationUserId;

    private String userIp;

    private String businessMark;//跟单员标识

    private Integer auditMark;//是否需要审核标识(0默认需要 1不需要)
}
