package com.jugu.www.pcbonlinev2.domain.entity;


import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户DO实体类
 */
@Data
@TableName("user")
public class UserDO implements Serializable {
    private static final long serialVersionUID = -1957436261893417540L;

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String userName;

    private String email;

    private String password;

    private String skypeId;

    private String googleId;

    private String facebookId;

    private String mobilePhone;

    private String address;

    private String country;

    private String city;

    private String businessType;

    private String jobrole;

    private String applications;

    private Byte invalidMark;

    @TableField(fill = FieldFill.INSERT)
    private String favicon;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;

    @TableField(fill = FieldFill.INSERT)
    private String uuid;

    private Integer businessId;

    private String  businessName;

    private String userSystemId;

    private String facebookLoginId;

    private String googleLoginId;

    private Integer invitationUserId;

    @TableField(fill = FieldFill.INSERT)
    private String userIp;

    private Integer auditMark;//是否需要审核标识(0默认需要 1不需要)

}

