package com.jugu.www.pcbonlinev2.state.handler;

import lombok.Data;

/**
 * 抽象订单处理类
 */
@Data
public abstract class AbstractOrderProcessor {
    /**
     * 当前状态
     */
    int status;

    /**
     * 具体业务实现分
     * @param orderId 订单id
     * @param params 具体实体类
     * @return 是否成功
     */
    public abstract boolean process(Integer orderId, Object... params);
}
