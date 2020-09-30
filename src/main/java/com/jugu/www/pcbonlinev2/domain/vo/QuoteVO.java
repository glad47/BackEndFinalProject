package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Data
public class QuoteVO implements Serializable {
    private static final long serialVersionUID = -4400545156111961870L;

    /**
     * 报价id
     */
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 默认值1(single pcb单只)2(panel as design 拼板）3（panel by pcbonline)
     */
    private Integer boardType;
    /**
     * 尺寸长
     */
    private BigDecimal dimensionsX;
    /**
     * 尺寸宽
     */
    private BigDecimal dimensionsY;
    /**
     * panel长
     */
    private BigDecimal panelSizeX;
    /**
     * panel宽
     */
    private BigDecimal panelSizeY;
    /**
     * 拼版方式
     */
    private Integer panelWayX;
    /**
     * 拼版方式
     */
    private Integer panelWayY;
    /**
     * pcs数量
     */
    private Integer quantityPcs;
    /**
     * panel数量
     */
    private Integer quantityPanel;
    /**
     * 面积
     */
    private BigDecimal areaSq;
    /**
     * pcb类型
     */
    private String pcbType;
    /**
     * tg
     */
    private String tg;
    /**
     * 材料
     */
    private String material;
    /**
     * CTI
     */
    private String cti;
    /**
     * product品牌
     */
    private String productCode;
    /**
     * 层数
     */
    private Integer layerNum;
    /**
     * 无卤数
     */
    private String halogenFree;
    /**
     * 成品厚度
     */
    private String finishThickness;
    /**
     * 导热系数
     */
    private String heatConductivity;
    /**
     * 内层铜厚
     */
    private String innerLayerCopper;
    /**
     * 压合结构
     */
    private String stackUp;
    /**
     * 内层铜最小线距
     */
    private String innerMinTrack;
    /**
     * 内芯数量
     */
    private String nOfCore;
    /**
     * 内层铜最小间距
     */
    private String innerMinSpacing;
    /**
     * PP数量
     */
    private String nOfPp;
    /**
     * 外层铜厚
     */
    private String outerLayerCopper;
    /**
     * 外层铜最小线距
     */
    private String outerMinTrack;
    /**
     * BGA尺寸
     */
    private String bgaSize;
    /**
     * 外层铜最小间距
     */
    private String outerMinSpacing;
    /**
     * 最小孔径
     */
    private BigDecimal minHoleSize;
    /**
     * 孔径数量
     */
    private String nOfHoles;
    /**
     * 孔铜
     */
    private String pthCopper;
    /**
     * 阻焊top颜色
     */
    private String solderMaskTopColor;
    /**
     * 过孔方式
     */
    private String viaProcess;
    /**
     * 阻焊Bot颜色
     */
    private String solderMaskBotColor;
    /**
     * 字符top颜色
     */
    private String silkScreenTopColor;
    /**
     * 蓝胶
     */
    private String peelable;
    /**
     * 字符bot颜色
     */
    private String silkScreenBotColor;
    /**
     * 品牌
     */
    private String peelableBrand;
    /**
     * 表面处理
     */
    private String surfaceFinish;
    /**
     * 厚度
     */
    private String thickness;
    /**
     * 面积
     */
    private String surfaceArea;
    /**
     * 面板路由路径
     */
    private String panelRoutingPath;
    /**
     * 冲孔N.of holes
     */
    private String punchingHoles;
    /**
     * 冲孔N.of Slots
     */
    private String punchingSlots;
    /**
     * 测试点
     */
    private String testPoint;
    /**
     * 盲孔
     */
    private Integer blindHoles;
    /**
     * 金属包边
     */
    private Integer edgePlated;
    /**
     * 半孔
     */
    private Integer halfHoleLated;
    /**
     * 阻抗
     */
    private Integer contrlImpeance;
    /**
     * 埋孔
     */
    private Integer buriedHoles;
    /**
     * 碳油
     */
    private Integer carbon;
    /**
     * 斜边
     */
    private Integer bevellingCamfer;
    /**
     * 控深锣
     */
    private Integer deepMillRouting;
    /**
     *
     */
    private Integer backDrill;
    /**
     *
     */
    private Integer viaInPad;
    /**
     *
     */
    private Integer negativePostitiveCopper;
    /**
     *
     */
    private Integer pressHoles;
    /**
     *
     */
    private Integer countersinks;
    /**
     *
     */
    private Integer acceptableQualityLevels;
    /**
     * 上传的gerber文件路径
     */
    private String gerberPath;
    /**
     * gerber文件名
     */
    private String gerberName;
    /**
     * 备注
     */
    private String remark;
    /**
     * panel类型数量
     */
    private Integer differentDesign;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 逻辑主键
     */
    private String uuid;
    /**
     * 孔测试方式
     */
    private Integer testPoinType;
    /**
     * 板子费
     */
    private BigDecimal boardFee;
    /**
     * test费
     */
    private BigDecimal testCostFee;
    /**
     * 工程费
     */
    private BigDecimal engineeringFee;
    /**
     * 加急费
     */
    private BigDecimal overworkFee;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 孔测试方式(0 none 1 flying probe test 2 Test Fixture)
     */
    private Integer testPointType;
    /**
     * 建造时间
     */
    private String buildTime;
    /**
     * 状态(1待审核 2已审核待支付 3已支付 4待出货  5待运输 6 订单完成 7订单取消 8订单投诉)
     */
    private Integer status;
    /**
     * 是否存在钢网
     */
    private Integer isExistSmt;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 小计，工程费+板费+加急费 + 测试费
     */
    private BigDecimal subtotal;
    /**
     * 订单类型（1 默认为新单，2位返单）
     */
    private Integer orderType;
    /**
     * 是否锁定标识（1默认未锁定 2锁定）
     */
    private Integer isLock;
    /**
     * 客户填写的订单号
     */
    private String orderNo;
    /**
     * 水单
     */
    private String invoiceNo;
    /**
     * 订单支付时间
     */
    private Date orderTime;
    /**
     * 是否存在贴片
     */
    private Integer isAssembly;
    /**
     * 单价（板子费/pcs数）
     */
    private BigDecimal unitPrice;
    /**
     * 跟单员id
     */
    private Integer businessId;
    /**
     * 快递公司
     */
    private String courierName;
    /**
     * 快递单号
     */
    private String courierNo;
    /**
     * 支付订单id
     */
    private Long payLogId;
    /**
     * 蓝胶
     */
    private Integer peelMark;
    /**
     * 开始的状态
     */
    private Integer firstStatus;
    /**
     * 跟单员名字
     */
    private String businessName;
    /**
     * 产品类型
     */
    private Integer oType;
    /**
     * 生产流程名
     */
    private String production;

}
