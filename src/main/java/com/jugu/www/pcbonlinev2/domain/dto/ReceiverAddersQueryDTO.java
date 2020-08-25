package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 收货地址
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@Data
@ApiModel(value = "收货地址")
public class ReceiverAddersQueryDTO implements Serializable {
    private static final long serialVersionUID = 7495854261755483873L;

    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "收货公司全名")
    private String receiverCompany;
    @ApiModelProperty(value = "收货邮箱")
    private String receiverEmail;
    @ApiModelProperty(value = "收货人姓名")
    private String receiverName;
    @ApiModelProperty(value = "收货人电话")
    private String receiverTelephone;
    @ApiModelProperty(value = "收货国家")
    private String receiverCountry;
    @ApiModelProperty(value = "收货国家名")
    private String receiverCountryName;
    @ApiModelProperty(value = "收货城市")
    private String receiverCity;
    @ApiModelProperty(value = "收货地邮箱编码")
    private String receiverPostcode;
    @ApiModelProperty(value = "收货详细地址")
    private String receiverAddress;
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "是否默认(0否 1是，默认为否)")
    private Integer isDefault;
    @ApiModelProperty(value = "指定快递名")
    private String courierName;
    @ApiModelProperty(value = "指定快递账号")
    private String courierAccount;

}
