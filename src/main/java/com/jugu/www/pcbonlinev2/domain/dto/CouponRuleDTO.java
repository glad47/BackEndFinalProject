package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 优惠劵生成规则表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class CouponRuleDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "优惠券规则名称")
    private String name;
    @ApiModelProperty(value = "规则描述")
    private String describe;
    @ApiModelProperty(value = "优惠券有效时间（天）")
    private Integer availableTime;
    @ApiModelProperty(value = "开始时间")
    private Date startTime;
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;
    @ApiModelProperty(value = "劵序列号长度（默认为9）")
    private String codeLen;
    @ApiModelProperty(value = "券标识码")
    private String codeFlag;
    @ApiModelProperty(value = "劵标识长度")
    private String codeFlagBitLen;
    @ApiModelProperty(value = "劵验证为长度")
    private String codeCheckBitLen;
    @ApiModelProperty(value = "券数量")
    private Integer codeNmber;
    @ApiModelProperty(value = "创建人id")
    private Integer createUser;
    @ApiModelProperty(value = "金额")
    private Integer money;

}
