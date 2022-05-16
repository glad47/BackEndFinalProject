package com.jugu.www.pcbonlinev2.config;

import com.jugu.www.pcbonlinev2.domain.dto.notification.NotificationQueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列相关配置
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    MessageConverter createMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    /**
     * 消息推送 队列绑定的交换机
     *
     * @return
     */
    @Bean
    DirectExchange notificationDirect() {
        return (DirectExchange) ExchangeBuilder.directExchange(NotificationQueueEnum.EXCHANGE_NAME.getExchange()).durable(true).build();
    }

    /**
     * 消息推送 邮箱审核发送队列
     *
     * @return
     */
    @Bean
    public Queue emailAuditQueue() {
        return new Queue(NotificationQueueEnum.QUEUE_EMAIL_AUDIT.getQueueName());
    }

    @Bean
    public Queue emailPayQueue(){
        return new Queue(NotificationQueueEnum.QUEUE_EMAIL_PAY.getQueueName());
    }

    /**
     * 消息推送 短信审核发送队列
     *
     * @return
     */
    @Bean
    public Queue smsAuditQueue() {
        return new Queue(NotificationQueueEnum.QUEUE_SMS_AUDIT.getQueueName());
    }

    @Bean
    public Queue smsPayQueue(){
        return new Queue(NotificationQueueEnum.QUEUE_SMS_PAY.getQueueName());
    }


    /**
     * 消息推送 将邮箱审核发送队列绑定到消息推送交换机。并确定路由名
     * @param notificationDirect
     * @param emailAuditQueue
     * @return
     */
    @Bean
    Binding emailAuditBinding(DirectExchange notificationDirect,Queue emailAuditQueue){
        return BindingBuilder.bind(emailAuditQueue).to(notificationDirect).with(NotificationQueueEnum.QUEUE_EMAIL_AUDIT.getRouteKey());
    }

    @Bean
    Binding emailPayBinding(DirectExchange notificationDirect, Queue emailPayQueue){
        return BindingBuilder.bind(emailPayQueue).to(notificationDirect).with(NotificationQueueEnum.QUEUE_EMAIL_PAY.getRouteKey());
    }

    @Bean
    Binding smsAuditBinding(DirectExchange notificationDirect, Queue smsAuditQueue){
        return BindingBuilder.bind(smsAuditQueue).to(notificationDirect).with(NotificationQueueEnum.QUEUE_SMS_AUDIT.getRouteKey());
    }

    @Bean
    Binding smsPayBinding(DirectExchange notificationDirect, Queue smsPayQueue){
        return BindingBuilder.bind(smsPayQueue).to(notificationDirect).with(NotificationQueueEnum.QUEUE_SMS_PAY.getRouteKey());
    }

}
