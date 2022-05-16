package com.jugu.www.pcbonlinev2.domain.dto.notification;


import lombok.Getter;

/**
 * 邮件模板名称枚举
 */
@Getter
public enum TemplateNameEnum {
    ORDER_AUDIT_EMAIL("mail-notice-template-order-pcb"),
    ORDER_PAY_EMAIL("mail-notice-template-order-pcb"),
    ORDER_AUDIT_SMS("SMS_175425232"),
    ORDER_PAY_SMS("SMS_175420523");

    private String name;
    TemplateNameEnum(String name) {
        this.name = name;
    }}
