package com.jugu.www.pcbonlinev2.service;

/**
 * 邮件发送接口
 */
public interface MailSendService {
    /**
     * 异步发送注册邮件
     * @param username
     */
    void asyncSendRegisterMail(String username);

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
}
