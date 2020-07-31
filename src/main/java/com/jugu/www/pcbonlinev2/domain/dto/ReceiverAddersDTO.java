package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import com.jugu.www.pcbonlinev2.utils.InsertValidationGroup;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;

/**
 * 收货地址
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@Data
public class ReceiverAddersDTO implements Serializable {
    private static final long serialVersionUID = -5887689779196205795L;

    @ApiModelProperty(value = "主键", hidden = true)
    private Integer id;
    @ApiModelProperty(value = "用户id", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "收货公司全名")
    private String receiverCompany;

    @ApiModelProperty(value = "收货邮箱")
    private String receiverEmail;

    @ApiModelProperty(value = "收货人姓名")
    @NotNull(message = "收货人姓名不能为空",groups = {InsertValidationGroup.class})
    private String receiverName;

    @NotNull(message = "收货人电话不能为空",groups = {InsertValidationGroup.class})
    @ApiModelProperty(value = "收货人电话")
    private String receiverTelephone;

    @NotNull(message = "收货国家不能为空",groups = {InsertValidationGroup.class})
    @ApiModelProperty(value = "收货国家")
    private String receiverCountry;

    @ApiModelProperty(value = "收货国家名")
    private String receiverCountryName;

    @NotNull(message = "收货城市不能为空",groups = {InsertValidationGroup.class})
    @ApiModelProperty(value = "收货城市")
    private String receiverCity;

    @NotNull(message = "收货地邮箱编码不能为空",groups = {InsertValidationGroup.class})
    @ApiModelProperty(value = "收货地邮箱编码")
    private String receiverPostcode;

    @NotNull(message = "收货详细地址不能为空",groups = {InsertValidationGroup.class})
    @ApiModelProperty(value = "收货详细地址")
    private String receiverAddress;

    @ApiModelProperty(value = "修改时间",hidden = true)
    private Date gmtModified;

    @ApiModelProperty(value = "创建时间",hidden = true)
    private Date gmtCreate;

    @ApiModelProperty(value = "是否默认(0否 1是，默认为否)")
    private Integer isDefault;

    @ApiModelProperty(value = "指定快递名")
    private String courierName;

    @ApiModelProperty(value = "指定快递账号")
    private String courierAccount;

}
