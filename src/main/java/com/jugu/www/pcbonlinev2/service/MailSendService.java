package com.jugu.www.pcbonlinev2.service;

import java.util.Map;

/**
 * 邮件发送接口
 */
public interface MailSendService {
    void asyncSendRegisterMail(String username);
}
