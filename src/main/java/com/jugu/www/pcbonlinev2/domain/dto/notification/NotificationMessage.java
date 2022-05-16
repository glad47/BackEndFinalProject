package com.jugu.www.pcbonlinev2.domain.dto.notification;

import lombok.Data;

import java.util.Map;

/**
 * 消息推送 信息封装对象
 */
@Data
public class NotificationMessage {
    private String templateName; //模板名
    private String receiver; //接收者
    private Map<String, Object> data; //数据

    public NotificationMessage() {
    }
}
