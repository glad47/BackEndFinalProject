package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.state.annotation.OrderOperator;
import com.jugu.www.pcbonlinev2.state.annotation.OrderProcessor;
import com.jugu.www.pcbonlinev2.state.annotation.StateHandler;
import com.jugu.www.pcbonlinev2.state.annotation.StateMachine;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderOperator;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderProcessor;
import com.jugu.www.pcbonlinev2.state.handler.AbstractStateHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Initialization implements BeanPostProcessor {
    @Resource
    OrderStateManager manager;

    public Object postProcessAfterInitialization(@SuppressWarnings("NullableProblems") Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractOrderOperator && bean.getClass().isAnnotationPresent(OrderOperator.class)){
            AbstractOrderOperator orderState = (AbstractOrderOperator) bean;
            manager.orderOperatorMaps.put(orderState.getStatus(),orderState);
        }

        if (bean instanceof AbstractOrderProcessor && bean.getClass().isAnnotationPresent(OrderProcessor.class)){
            AbstractOrderProcessor orderProcessor = (AbstractOrderProcessor) bean;
            manager.orderProcessorMaps.put(orderProcessor.getStatus(),orderProcessor);
        }

        if (bean instanceof AbstractStateMachine && bean.getClass().isAnnotationPresent(StateMachine.class)){
            AbstractStateMachine stateMachine = (AbstractStateMachine) bean;
            manager.stateMachineMaps.put(stateMachine.getStateMachineName(),stateMachine);
        }

        if (bean instanceof AbstractStateHandler && bean.getClass().isAnnotationPresent(StateHandler.class)){
            AbstractStateHandler stateHandler = (AbstractStateHandler) bean;
            manager.stateHandlerMaps.put(stateHandler.getStateHandlerMark(),stateHandler);
        }
        return bean;
    }
}
