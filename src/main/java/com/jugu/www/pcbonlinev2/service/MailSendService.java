package com.jugu.www.pcbonlinev2.service;

import java.math.BigDecimal;

/**
 * 邮件发送接口
 */
public interface MailSendService {
    /**
     * 异步发送注册邮件
     * @param username
     * @param form
     */
    void asyncSendRegisterMail(String username, String form);

    /**
     * 异步发送修改密码邮件
     * @param email
     */
    void asyncSendPasswordResetMail(String email);

    /**
     * 异步发送询盘邮件
     * @param email
     * @param name
     * @param msg
     */
    void asyncSendContactEmail(String email, String name, String msg);

    /**
     * 异步发送反馈邮件
     * @param email
     * @param name
     * @param msg
     */
    void asyncSendReviewEmail(String email, String name, String msg);

    /**
     * 发送面向管理者的通知邮件
     * @param msgType 订单类型
     * @param pns 订单型号
     * @param total 金额
     */
    void sendAllMsgEmail(int msgType, String pns, BigDecimal total);
}
