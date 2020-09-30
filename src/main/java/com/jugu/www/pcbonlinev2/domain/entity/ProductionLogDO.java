package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 生产日志
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-29 15:45:00
 */
@Data
@TableName("production_log")
public class ProductionLogDO implements Serializable {
    private static final long serialVersionUID = 6199411892738867062L;
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 产品id
     */
    private Integer productId;
    /**
     * 流程名
     */
    private String processName;
    /**
     * 流程时间
     */
    private Date processTime;
    /**
     * 产品类型(1pcb,2钢网,3贴片)
     */
    private Integer processType;

}
