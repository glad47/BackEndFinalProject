package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.state.constant.OrderStatusEnum;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderOperator;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderStateManager {
    Map<Integer, AbstractOrderOperator> orderOperatorMaps = new HashMap<>();
    Map<Integer, AbstractOrderProcessor> orderProcessorMaps = new HashMap<>();

    public OrderStateManager(){}

    /**
     * 状态流转方法
     * @param orderId  订单id
     * @param event  流转的订单操作事件
     * @param status  当前订单状态
     * @return 扭转后的订单状态
     */
    public int handleEvent(final Integer orderId, OrderStatusEnum event, final int status){
        if (this.isFinalStatus(status)) throw new IllegalStateException("最终状态不用处理");

        AbstractOrderOperator abstractOrderOperator = this.getStateOperator(event);
        int resState = abstractOrderOperator.handleEvent(status, event);

        AbstractOrderProcessor orderProcessor = this.getOrderProcessor(event);

        if(!orderProcessor.process(orderId,resState)) throw new IllegalStateException(String.format("订单状态流转失败，订单id:%s",orderId));

        return resState;

    }

    /**
     * 获取具体业务处理器
     * @param event 流转的订单操作事件
     * @return 具体操作处理类
     */
    private AbstractOrderProcessor getOrderProcessor(OrderStatusEnum event) {
        AbstractOrderProcessor processor = null;
        for (Map.Entry<Integer, AbstractOrderProcessor> entry : orderProcessorMaps.entrySet()) {
            if (event.status == entry.getKey()) {
                processor = entry.getValue();
            }
        }
        if (null == processor) {
            throw new IllegalArgumentException(String.format("can't find proper processor. The parameter state :%s", event.toString()));
        }
        return processor;
    }

    /**
     * 获取状态处理器
     * @param event 流转的订单操作事件
     * @return 具体状态类
     */
    private AbstractOrderOperator getStateOperator(OrderStatusEnum event) {
        AbstractOrderOperator operator = null;
        for (Map.Entry<Integer, AbstractOrderOperator> entry: orderOperatorMaps.entrySet()) {
            if (event.status == entry.getKey()) {
                operator = entry.getValue();
            }
        }
        if (null == operator) {
            throw new IllegalArgumentException(String.format("can't find proper operator. The parameter state :%s", event.toString()));
        }
        return operator;
    }

    private boolean isFinalStatus(int status) {
        return OrderStatusEnum.ORDER_FINISHED.status == status;
    }
}
