package com.jugu.www.pcbonlinev2.domain.vo;

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
public class ProductionLogVO implements Serializable {
    private static final long serialVersionUID = 5294529611807474851L;

    /**
     * id
     */
    //private Integer id;
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
    //private Integer processType;

}
