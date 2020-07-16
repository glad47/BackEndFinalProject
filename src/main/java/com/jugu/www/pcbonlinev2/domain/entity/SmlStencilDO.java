package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 钢网订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@Data
@TableName("sml_stencil")
public class SmlStencilDO implements Serializable {
    private static final long serialVersionUID = -8024838732913953315L;
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 报价id
     */
    private Integer quoteId;
    /**
     * 钢网类型
     */
    private String stencilType;
    /**
     * 钢网侧边
     */
    private String stencilSide;
    /**
     * 大小(钢网价格配置表id)
     */
    private Integer size;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 厚度
     */
    private String thickness;
    /**
     * 基准
     */
    private String existingFiducials;
    /**
     * 注释
     */
    private String note;
    /**
     * 大小x
     */
    private String stencilSizeX;
    /**
     * 大小y
     */
    private String stencilSizeY;
    /**
     * 面积x
     */
    private String stencilAreaX;
    /**
     * 面积y
     */
    private String stencilAreaY;
    /**
     * 钢网总价
     */
    private BigDecimal totalStencilFee;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 重量
     */
    private BigDecimal weight;
    /**
     * 上传的gerber文件路径
     */
    private String gerberPath;
    /**
     * gerber文件名
     */
    private String gerberName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 订单id
     */
    private Integer orderId;
    /**
     * 是否锁定
     */
    private Integer isLock;
    /**
     * 客户的订单id
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
     * 逻辑id
     */
    private String uuid;
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
     * 开始的状态
     */
    private Integer firstStatus;
    /**
     * 跟单员名字
     */
    private String businessName;

}
