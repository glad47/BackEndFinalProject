package com.jugu.www.pcbonlinev2.domain.dto;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteDTO implements Serializable {
	// TODO serialVersionUid

		@ApiModelProperty(value = "报价id")
	private Integer id;
		@ApiModelProperty(value = "用户id")
	private Integer userId;
		@ApiModelProperty(value = "订单id")
	private Integer orderId;
		@ApiModelProperty(value = "默认值1(single pcb单只)2(panel as design 拼板）3（panel by pcbonline)")
	private Integer boardType;
		@ApiModelProperty(value = "尺寸长")
	private BigDecimal dimensionsX;
		@ApiModelProperty(value = "尺寸宽")
	private BigDecimal dimensionsY;
		@ApiModelProperty(value = "panel长")
	private BigDecimal panelSizeX;
		@ApiModelProperty(value = "panel宽")
	private BigDecimal panelSizeY;
		@ApiModelProperty(value = "拼版方式")
	private Integer panelWayX;
		@ApiModelProperty(value = "拼版方式")
	private Integer panelWayY;
		@ApiModelProperty(value = "pcs数量")
	private Integer quantityPcs;
		@ApiModelProperty(value = "panel数量")
	private Integer quantityPanel;
		@ApiModelProperty(value = "面积")
	private BigDecimal areaSq;
		@ApiModelProperty(value = "pcb类型")
	private String pcbType;
		@ApiModelProperty(value = "tg")
	private String tg;
		@ApiModelProperty(value = "材料")
	private String material;
		@ApiModelProperty(value = "CTI")
	private String cti;
		@ApiModelProperty(value = "product品牌")
	private String productCode;
		@ApiModelProperty(value = "层数")
	private Integer layerNum;
		@ApiModelProperty(value = "无卤数")
	private String halogenFree;
		@ApiModelProperty(value = "成品厚度")
	private String finishThickness;
		@ApiModelProperty(value = "导热系数")
	private String heatConductivity;
		@ApiModelProperty(value = "内层铜厚")
	private String innerLayerCopper;
		@ApiModelProperty(value = "压合结构")
	private String stackUp;
		@ApiModelProperty(value = "内层铜最小线距")
	private String innerMinTrack;
		@ApiModelProperty(value = "内芯数量")
	private String nOfCore;
		@ApiModelProperty(value = "内层铜最小间距")
	private String innerMinSpacing;
		@ApiModelProperty(value = "PP数量")
	private String nOfPp;
		@ApiModelProperty(value = "外层铜厚")
	private String outerLayerCopper;
		@ApiModelProperty(value = "外层铜最小线距")
	private String outerMinTrack;
		@ApiModelProperty(value = "BGA尺寸")
	private String bgaSize;
		@ApiModelProperty(value = "外层铜最小间距")
	private String outerMinSpacing;
		@ApiModelProperty(value = "最小孔径")
	private BigDecimal minHoleSize;
		@ApiModelProperty(value = "孔径数量")
	private String nOfHoles;
		@ApiModelProperty(value = "孔铜")
	private String pthCopper;
		@ApiModelProperty(value = "阻焊top颜色")
	private String solderMaskTopColor;
		@ApiModelProperty(value = "过孔方式")
	private String viaProcess;
		@ApiModelProperty(value = "阻焊Bot颜色")
	private String solderMaskBotColor;
		@ApiModelProperty(value = "字符top颜色")
	private String silkScreenTopColor;
		@ApiModelProperty(value = "蓝胶")
	private String peelable;
		@ApiModelProperty(value = "字符bot颜色")
	private String silkScreenBotColor;
		@ApiModelProperty(value = "品牌")
	private String peelableBrand;
		@ApiModelProperty(value = "表面处理")
	private String surfaceFinish;
		@ApiModelProperty(value = "厚度")
	private String thickness;
		@ApiModelProperty(value = "面积")
	private String surfaceArea;
		@ApiModelProperty(value = "面板路由路径")
	private String panelRoutingPath;
		@ApiModelProperty(value = "冲孔N.of holes")
	private String punchingHoles;
		@ApiModelProperty(value = "冲孔N.of Slots")
	private String punchingSlots;
		@ApiModelProperty(value = "测试点")
	private String testPoint;
		@ApiModelProperty(value = "盲孔")
	private Integer blindHoles;
		@ApiModelProperty(value = "金属包边")
	private Integer edgePlated;
		@ApiModelProperty(value = "半孔")
	private Integer halfHoleLated;
		@ApiModelProperty(value = "阻抗")
	private Integer contrlImpeance;
		@ApiModelProperty(value = "埋孔")
	private Integer buriedHoles;
		@ApiModelProperty(value = "碳油")
	private Integer carbon;
		@ApiModelProperty(value = "斜边")
	private Integer bevellingCamfer;
		@ApiModelProperty(value = "控深锣")
	private Integer deepMillRouting;
		@ApiModelProperty(value = "")
	private Integer backDrill;
		@ApiModelProperty(value = "")
	private Integer viaInPad;
		@ApiModelProperty(value = "")
	private Integer negativePostitiveCopper;
		@ApiModelProperty(value = "")
	private Integer pressHoles;
		@ApiModelProperty(value = "")
	private Integer countersinks;
		@ApiModelProperty(value = "")
	private Integer acceptableQualityLevels;
		@ApiModelProperty(value = "上传的gerber文件路径")
	private String gerberPath;
		@ApiModelProperty(value = "gerber文件名")
	private String gerberName;
		@ApiModelProperty(value = "备注")
	private String remark;
		@ApiModelProperty(value = "panel类型数量")
	private Integer differentDesign;
		@ApiModelProperty(value = "修改时间")
	private Date gmtModified;
		@ApiModelProperty(value = "创建时间")
	private Date gmtCreate;
		@ApiModelProperty(value = "逻辑主键")
	private String uuid;
		@ApiModelProperty(value = "孔测试方式")
	private Integer testPoinType;
		@ApiModelProperty(value = "板子费")
	private BigDecimal boardFee;
		@ApiModelProperty(value = "test费")
	private BigDecimal testCostFee;
		@ApiModelProperty(value = "工程费")
	private BigDecimal engineeringFee;
		@ApiModelProperty(value = "加急费")
	private BigDecimal overworkFee;
		@ApiModelProperty(value = "产品编号")
	private String productNo;
		@ApiModelProperty(value = "孔测试方式(0 none 1 flying probe test 2 Test Fixture)")
	private Integer testPointType;
		@ApiModelProperty(value = "建造时间")
	private String buildTime;
		@ApiModelProperty(value = "状态(1待审核 2已审核待支付 3已支付 4待出货  5待运输 6 订单完成 7订单取消 8订单投诉)")
	private Integer status;
		@ApiModelProperty(value = "是否存在钢网")
	private Integer isExistSmt;
		@ApiModelProperty(value = "重量")
	private BigDecimal weight;
		@ApiModelProperty(value = "小计，工程费+板费+加急费 + 测试费")
	private BigDecimal subtotal;
		@ApiModelProperty(value = "订单类型（1 默认为新单，2位返单）")
	private Integer orderType;
		@ApiModelProperty(value = "是否锁定标识（1默认未锁定 2锁定）")
	private Integer isLock;
		@ApiModelProperty(value = "客户填写的订单号")
	private String orderNo;
		@ApiModelProperty(value = "水单")
	private String invoiceNo;
		@ApiModelProperty(value = "订单支付时间")
	private Date orderTime;
		@ApiModelProperty(value = "是否存在贴片")
	private Integer isAssembly;
		@ApiModelProperty(value = "单价（板子费/pcs数）")
	private BigDecimal unitPrice;
		@ApiModelProperty(value = "跟单员id")
	private Integer businessId;
		@ApiModelProperty(value = "快递公司")
	private String courierName;
		@ApiModelProperty(value = "快递单号")
	private String courierNo;
		@ApiModelProperty(value = "支付订单id")
	private Long payLogId;
		@ApiModelProperty(value = "蓝胶")
	private Integer peelMark;
		@ApiModelProperty(value = "开始的状态")
	private Integer firstStatus;
		@ApiModelProperty(value = "跟单员名字")
	private String businessName;
	
}
