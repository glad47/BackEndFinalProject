package com.jugu.www.pcbonlinev2.service;

/**
 * 邮件发送接口
 */
public interface MailSendService {
    void asyncSendRegisterMail(String username);

    void asyncSendPasswordResetMail(String email);
}
