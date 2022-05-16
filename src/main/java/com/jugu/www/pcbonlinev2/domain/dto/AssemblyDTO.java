package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 切片订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Data
public class AssemblyDTO implements Serializable {
    private static final long serialVersionUID = 1485643622062936155L;

    @ApiModelProperty(value = "id")
    private Integer id;
    @ApiModelProperty(value = "用户id")
    private Integer userId;
    @ApiModelProperty(value = "报价id")
    private Integer quoteId;
    @ApiModelProperty(value = "物料来源类型")
    private Integer assemblyType;
    @ApiModelProperty(value = "物料种类")
    private Integer uniquePartNum;
    @ApiModelProperty(value = "smt点数")
    private Integer smtPartNum;
    @ApiModelProperty(value = "插件点数")
    private Integer throughHolePartNum;
    @ApiModelProperty(value = "单双面")
    private String assemblySide;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "数量")
    private Integer quantity;
    @ApiModelProperty(value = "上传的gerber文件路径")
    private String gerberPath;
    @ApiModelProperty(value = "文件名称")
    private String gerberName;
    @ApiModelProperty(value = "状态")
    private Integer status;
    @ApiModelProperty(value = "订单号")
    private Integer orderId;
    @ApiModelProperty(value = "是否锁定")
    private Integer isLock;
    @ApiModelProperty(value = "客户的订单id")
    private String orderNo;
    @ApiModelProperty(value = "水单")
    private String invoiceNo;
    @ApiModelProperty(value = "订单支付时间")
    private Date orderTime;
    @ApiModelProperty(value = "")
    private Date gmtCreate;
    @ApiModelProperty(value = "")
    private Date gmtModified;
    @ApiModelProperty(value = "产品编号")
    private String productNo;
    @ApiModelProperty(value = "贴片总价")
    private BigDecimal totalAssemblyFee;
    @ApiModelProperty(value = "逻辑id")
    private String uuid;
    @ApiModelProperty(value = "支付订单id")
    private Long payLogId;
    @ApiModelProperty(value = "汇率id(默认为1 美元)")
    private Integer exchangeId;
    @ApiModelProperty(value = "跟单员id")
    private Integer businessId;
    @ApiModelProperty(value = "跟单员name")
    private String businessName;
    @ApiModelProperty(value = "回款到账时间")
    private Date payTime;
    @ApiModelProperty(value = "是否为内部订单（1是 2否）")
    private Integer isInternal;

}
