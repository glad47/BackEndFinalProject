package com.jugu.www.pcbonlinev2.aspect;

import com.jugu.www.pcbonlinev2.domain.dto.notification.NotificationMessage;
import com.jugu.www.pcbonlinev2.domain.dto.notification.NotificationQueueEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息推送 生产者
 */
@Slf4j
@Component
public class NotificationSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(NotificationMessage message, String routeKey){
        rabbitTemplate.convertAndSend(NotificationQueueEnum.EXCHANGE_NAME.getExchange(),routeKey,message);
    }
}
