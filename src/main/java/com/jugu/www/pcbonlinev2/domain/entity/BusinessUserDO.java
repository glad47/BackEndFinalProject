package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("business_user")
public class BusinessUserDO {
    @TableId
    private Integer id;
    private String name;
    private String contactEmail; //邮箱
    private String telephone;   //电话
    private String prefixNo;    //前缀
    private Integer customerNo; //客户编号
    private Integer score;
    private Integer businessId;//跟单员id 即erp系统中user角色为跟单员的userId
    private String avatar;

}
