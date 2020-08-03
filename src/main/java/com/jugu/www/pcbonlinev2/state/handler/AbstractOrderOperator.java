package com.jugu.www.pcbonlinev2.state.handler;

import com.jugu.www.pcbonlinev2.state.constant.OrderStatusEnum;
import lombok.Data;

/**
 * 抽象订单操作类
 */
@Data
public abstract class AbstractOrderOperator {
    /**
     * 当前状态
     */
    int status;

    /**
     * 操作处理方法
     * @param orderStatus 订单状态
     * @param orderStatusEnum 订单操作事件
     * @return 下一个状态
     */
    public abstract int handleEvent(int orderStatus, OrderStatusEnum orderStatusEnum);
}
