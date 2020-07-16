package com.jugu.www.pcbonlinev2.domain.vo;

import java.math.BigDecimal;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 报价公式表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 17:47:29
 */
@Data
public class QuoteConfigVO implements Serializable {
    private static final long serialVersionUID = -7656794364118603956L;

    /**
     * id
     */
    private Integer id;
    /**
     * 面积 id
     */
    private Integer areaSq;
    /**
     * 基材对应pcbtypeid
     */
    private Integer pcbtype;
    /**
     * 层数id
     */
    private Integer layerNum;
    /**
     * 加急类型id
     */
    private Integer urgentType;
    /**
     * 参数类型id
     */
    private Integer parameterType;
    /**
     * 参数名称id
     */
    private Integer parameterName;
    /**
     * 判断条件
     */
    private String quoteCondition;
    /**
     * 参数值id
     */
    private Integer parameter;
    /**
     * 加价类别id
     */
    private Integer premiumType;
    /**
     * 报价
     */
    private BigDecimal quote;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 是否删除（1表示是，0表示否）
     */
    private Integer isDelete;

}
