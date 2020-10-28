package com.jugu.www.pcbonlinev2.service;

/**
 * 发送短信接口
 */
public interface SmsSendService {
    /**
     * 发送短信
     * @param phoneNumber
     * @param templateCode
     * @param templateParam
     */
    void sendSms(String phoneNumber,String templateCode,String templateParam);

    /**
     * 发送审核短信
     * @param phoneNumber
     * @param smg
     */
    void sendAuditSmsMsg(String phoneNumber,String smg);

    /**
     * 发送支付短信
     * @param phone
     * @param smg
     */
    void sendPaySmsMsg(String phone, String smg);
}
