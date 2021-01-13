package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 切片订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Data
@TableName("assembly")
public class AssemblyDO implements Serializable {
    private static final long serialVersionUID = -6243191415556376028L;
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
     * 物料来源类型
     */
    private Integer assemblyType;
    /**
     * 物料种类
     */
    private Integer uniquePartNum;
    /**
     * smt点数
     */
    private Integer smtPartNum;
    /**
     * 插件点数
     */
    private Integer throughHolePartNum;
    /**
     * 单双面
     */
    private String assemblySide;
    /**
     * 备注
     */
    private String remark;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 上传的gerber文件路径
     */
    private String gerberPath;
    /**
     * 文件名称
     */
    private String gerberName;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 订单号
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
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    /**
     * 产品编号
     */
    private String productNo;
    /**
     * 贴片总价
     */
    private BigDecimal totalAssemblyFee;
    /**
     * 逻辑id
     */
    @TableField(fill = FieldFill.INSERT)
    private String uuid;
    /**
     * 支付订单id
     */
    private Long payLogId;
    /**
     * 汇率id(默认为1 美元)
     */
    private Integer exchangeId;
    /**
     * 跟单员id
     */
    private Integer businessId;
    /**
     * 跟单员name
     */
    private String businessName;
    /**
     * 回款到账时间
     */
    private Date payTime;
    /**
     * 是否为内部订单（1是 2否）
     */
    private Integer isInternal;

}
