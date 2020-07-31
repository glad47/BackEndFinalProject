package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

/**
 * 收货地址
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@Data
@TableName("receiver_adders")
public class ReceiverAddersDO implements Serializable {
    private static final long serialVersionUID = -6344237483311243670L;
    /**
     * 主键
     */
    @TableId
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 收货公司全名
     */
    private String receiverCompany;
    /**
     * 收货邮箱
     */
    private String receiverEmail;
    /**
     * 收货人姓名
     */
    private String receiverName;
    /**
     * 收货人电话
     */
    private String receiverTelephone;
    /**
     * 收货国家
     */
    private String receiverCountry;
    /**
     * 收货国家名
     */
    private String receiverCountryName;
    /**
     * 收货城市
     */
    private String receiverCity;
    /**
     * 收货地邮箱编码
     */
    private String receiverPostcode;
    /**
     * 收货详细地址
     */
    private String receiverAddress;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime gmtModified;
    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime gmtCreate;
    /**
     * 是否默认(0否 1是，默认为否)
     */
    private Integer isDefault;
    /**
     * 指定快递名
     */
    private String courierName;
    /**
     * 指定快递账号
     */
    private String courierAccount;

}
