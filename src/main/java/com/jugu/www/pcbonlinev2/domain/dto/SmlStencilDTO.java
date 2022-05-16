package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 钢网订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@Data
public class SmlStencilDTO implements Serializable {
    private static final long serialVersionUID = -4785568013798953914L;

    @ApiModelProperty(value = "id")
    private Integer id; //id
    @ApiModelProperty(value = "用户id")
    private Integer userId;//用户id
    @ApiModelProperty(value = "报价id")
    private Integer quoteId;//报价id
    @ApiModelProperty(value = "钢网类型")
    private String stencilType;//钢网类型
    @ApiModelProperty(value = "钢网侧边")
    private String stencilSide;//钢网侧边
    @ApiModelProperty(value = "大小(钢网价格配置表id)")
    private Integer size;//大小(钢网价格配置表id)
    @ApiModelProperty(value = "数量")
    private Integer quantity;//数量
    @ApiModelProperty(value = "厚度")
    private String thickness;//厚度
    @ApiModelProperty(value = "基准")
    private String existingFiducials;//基准
    @ApiModelProperty(value = "注释")
    private String note;//注释
    @ApiModelProperty(value = "大小x")
    private String stencilSizeX;//大小x
    @ApiModelProperty(value = "大小y")
    private String stencilSizeY;//大小y
    @ApiModelProperty(value = "面积x")
    private String stencilAreaX;//面积x
    @ApiModelProperty(value = "面积y")
    private String stencilAreaY;//
    @ApiModelProperty(value = "钢网总价")
    private BigDecimal totalStencilFee;//钢网总价
    @ApiModelProperty(value = "产品编号")
    private String productNo;//产品编号
    @ApiModelProperty(value = "创建时间")
    private Date gmtCreate;//创建时间
    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;//修改时间
    @ApiModelProperty(value = "重量")
    private BigDecimal weight;//重量
    @ApiModelProperty(value = "上传的gerber文件路径")
    private String gerberPath;//上传的gerber文件路径
    @ApiModelProperty(value = "gerber文件名")
    private String gerberName;//gerber文件名
    @ApiModelProperty(value = "状态")
    private Integer status;//状态
    @ApiModelProperty(value = "订单id")
    private Integer orderId;//订单id
    @ApiModelProperty(value = "是否锁定")
    private Integer isLock;//是否锁定
    @ApiModelProperty(value = "客户的订单id")
    private String orderNo;//客户的订单id
    @ApiModelProperty(value = "水单")
    private String invoiceNo;//水单"
    @ApiModelProperty(value = "订单支付时间")
    private Date orderTime;//订单支付时间
    @ApiModelProperty(value = "逻辑id")
    private String uuid;//逻辑id
    @ApiModelProperty(value = "跟单员id")
    private Integer businessId;//跟单员id
    @ApiModelProperty(value = "快递公司")
    private String courierName;//快递公司
    @ApiModelProperty(value = "快递单号")
    private String courierNo;//快递单号
    @ApiModelProperty(value = "支付订单id")
    private Long payLogId;//支付订单id
    @ApiModelProperty(value = "开始的状态")
    private Integer firstStatus;//开始的状态
    @ApiModelProperty(value = "跟单员名字")
    private String businessName;//跟单员名字


}
