package com.jugu.www.pcbonlinev2.domain.dto;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会员等级表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class MemberLevelDTO implements Serializable {
    // TODO serialVersionUid

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "等级")
    private String levelMember;
    @ApiModelProperty(value = "点数范围")
    private Integer minPoint;
    @ApiModelProperty(value = "点数范围")
    private Integer maxPoint;
    @ApiModelProperty(value = "优惠值(1整数)")
    private Integer preferentialDetail;
    @ApiModelProperty(value = "优惠百分比")
    private String membersStr;

}
