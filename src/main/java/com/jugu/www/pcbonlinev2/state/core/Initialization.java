package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.state.annotation.OrderOperator;
import com.jugu.www.pcbonlinev2.state.annotation.OrderProcessor;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderOperator;
import com.jugu.www.pcbonlinev2.state.handler.AbstractOrderProcessor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class Initialization implements BeanPostProcessor {
    @Resource
    OrderStateManager manager;

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof AbstractOrderOperator && bean.getClass().isAnnotationPresent(OrderOperator.class)){
            AbstractOrderOperator orderState = (AbstractOrderOperator) bean;
            manager.orderOperatorMaps.put(orderState.getStatus(),orderState);
        }

        if (bean instanceof AbstractOrderProcessor && bean.getClass().isAnnotationPresent(OrderProcessor.class)){
            AbstractOrderProcessor orderProcessor = (AbstractOrderProcessor) bean;
            manager.orderProcessorMaps.put(orderProcessor.getStatus(),orderProcessor);
        }
        return bean;
    }
}
