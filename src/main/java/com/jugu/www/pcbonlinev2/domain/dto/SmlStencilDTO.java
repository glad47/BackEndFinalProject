package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

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
	private Integer id;
		@ApiModelProperty(value = "用户id")
	private Integer userId;
		@ApiModelProperty(value = "报价id")
	private Integer quoteId;
		@ApiModelProperty(value = "钢网类型")
	private String stencilType;
		@ApiModelProperty(value = "钢网侧边")
	private String stencilSide;
		@ApiModelProperty(value = "大小(钢网价格配置表id)")
	private Integer size;
		@ApiModelProperty(value = "数量")
	private Integer quantity;
		@ApiModelProperty(value = "厚度")
	private String thickness;
		@ApiModelProperty(value = "基准")
	private String existingFiducials;
		@ApiModelProperty(value = "注释")
	private String note;
		@ApiModelProperty(value = "大小x")
	private String stencilSizeX;
		@ApiModelProperty(value = "大小y")
	private String stencilSizeY;
		@ApiModelProperty(value = "面积x")
	private String stencilAreaX;
		@ApiModelProperty(value = "面积y")
	private String stencilAreaY;
		@ApiModelProperty(value = "钢网总价")
	private BigDecimal totalStencilFee;
		@ApiModelProperty(value = "产品编号")
	private String productNo;
		@ApiModelProperty(value = "创建时间")
	private Date gmtCreate;
		@ApiModelProperty(value = "修改时间")
	private Date gmtModified;
		@ApiModelProperty(value = "重量")
	private BigDecimal weight;
		@ApiModelProperty(value = "上传的gerber文件路径")
	private String gerberPath;
		@ApiModelProperty(value = "gerber文件名")
	private String gerberName;
		@ApiModelProperty(value = "状态")
	private Integer status;
		@ApiModelProperty(value = "订单id")
	private Integer orderId;
		@ApiModelProperty(value = "是否锁定")
	private Integer isLock;
		@ApiModelProperty(value = "客户的订单id")
	private String orderNo;
		@ApiModelProperty(value = "水单")
	private String invoiceNo;
		@ApiModelProperty(value = "订单支付时间")
	private Date orderTime;
		@ApiModelProperty(value = "逻辑id")
	private String uuid;
		@ApiModelProperty(value = "跟单员id")
	private Integer businessId;
		@ApiModelProperty(value = "快递公司")
	private String courierName;
		@ApiModelProperty(value = "快递单号")
	private String courierNo;
		@ApiModelProperty(value = "支付订单id")
	private Long payLogId;
		@ApiModelProperty(value = "开始的状态")
	private Integer firstStatus;
		@ApiModelProperty(value = "跟单员名字")
	private String businessName;
	
}
