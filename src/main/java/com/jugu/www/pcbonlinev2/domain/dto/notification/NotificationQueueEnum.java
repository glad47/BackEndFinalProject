package com.jugu.www.pcbonlinev2.domain.dto.notification;

import lombok.Getter;

/**
 * 消息通知队列枚举类
 */
@Getter
public enum NotificationQueueEnum {
    EXCHANGE_NAME("pcbonline.notification.direct"),
    QUEUE_EMAIL_AUDIT("pcbonline.email.audit","pcbonline.email.audit"),
    QUEUE_EMAIL_PAY("pcbonline.email.pay","pcbonline.email.pay"),
    QUEUE_SMS_AUDIT("pcbonline.sms.audit","pcbonline.sms.audit"),
    QUEUE_SMS_PAY("pcbonline.sms.pay","pcbonline.sms.pay");

    /**
     * 交换机名称
     */
    private String exchange;

    /**
     * 队列名称
     */
    private String queueName;

    /**
     * 路由键
     */
    private String routeKey;

    NotificationQueueEnum(String exchange, String queueName, String routeKey) {
        this.exchange = exchange;
        this.queueName = queueName;
        this.routeKey = routeKey;
    }

    NotificationQueueEnum(String queueName, String routeKey) {
        this.queueName = queueName;
        this.routeKey = routeKey;
    }

    NotificationQueueEnum(String exchange) {
        this.exchange = exchange;
    }}
