package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.state.constant.Event;
import com.jugu.www.pcbonlinev2.state.constant.State;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderOperator;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderProcessor;
import com.jugu.www.pcbonlinev2.state.handler.AbstractStateHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderStateManager {
    Map<Integer, AbstractOrderOperator> orderOperatorMaps = new HashMap<>();
    Map<Integer, AbstractOrderProcessor> orderProcessorMaps = new HashMap<>();

    Map<String, AbstractStateMachine> stateMachineMaps = new HashMap<>();
    Map<Integer, AbstractStateHandler> stateHandlerMaps = new HashMap<>();

    public OrderStateManager(){}

    /**
     * 状态流转方法
     * @param orderId  订单id
     * @param event  流转的订单操作事件
     * @param status  当前订单状态
     * @return 扭转后的订单状态
     */
    public int handleEvent(final Integer orderId, State event, final int status){
        if (this.isFinalStatus(status)) throw new IllegalStateException("最终状态不用处理");

        AbstractOrderOperator abstractOrderOperator = this.getStateOperator(event);
        int resState = abstractOrderOperator.handleEvent(status, event);

        AbstractOrderProcessor orderProcessor = this.getOrderProcessor(event);

        if(!orderProcessor.process(orderId,resState)) throw new IllegalStateException(String.format("订单状态流转失败，订单id:%s",orderId));

        return resState;

    }

    public int post(final Integer orderId, Event event, final State state){
        if (this.isFinalStatus(state.status)) throw new IllegalStateException("最终状态不用处理");
        AbstractStateMachine abstractStateMachine = this.getStateMachine(event);
        State reState = abstractStateMachine.next(state, event);
        AbstractStateHandler abstractStateHandler = this.getStateHandler(event);
        if(!abstractStateHandler.handler(reState)) throw new IllegalStateException(String.format("订单状态流转失败，订单id:%s",orderId));
        return reState.status;
    }

    private AbstractStateHandler getStateHandler(Event event) {
        AbstractStateHandler handler =null;

        for (Map.Entry<Integer, AbstractStateHandler> entry: stateHandlerMaps.entrySet()) {
            if (event.status == entry.getKey()){
                handler = entry.getValue();
            }
        }
        if (null == handler) throw new IllegalStateException(String.format("找不到合适的处理机。参数状态: %s",event.toString()));

        return handler;
    }

    private AbstractStateMachine getStateMachine(Event event) {
        AbstractStateMachine machine = null;
        for (Map.Entry<String, AbstractStateMachine> entry : stateMachineMaps.entrySet()){
            if (event.mark.equals(entry.getKey())){
                machine = entry.getValue();
            }
        }
        if (null == machine){
            throw new IllegalStateException(String.format("找不到合适的状态机。参数状态: %s",event.toString()));
        }

        return machine;

    }

    /**
     * 获取具体业务处理器
     * @param event 流转的订单操作事件
     * @return 具体操作处理类
     */
    private AbstractOrderProcessor getOrderProcessor(State event) {
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
    private AbstractOrderOperator getStateOperator(State event) {
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
        return State.ORDER_FINISHED.status == status;
    }
}
