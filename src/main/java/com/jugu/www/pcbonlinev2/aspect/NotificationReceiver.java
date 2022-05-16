package com.jugu.www.pcbonlinev2.aspect;

import com.google.gson.Gson;
import com.jugu.www.pcbonlinev2.domain.dto.notification.NotificationMessage;
import com.jugu.www.pcbonlinev2.domain.dto.notification.NotificationQueueEnum;
import com.jugu.www.pcbonlinev2.domain.vo.AuditSmsVo;
import com.jugu.www.pcbonlinev2.domain.vo.PaySmsVo;
import com.jugu.www.pcbonlinev2.service.MailSendService;
import com.jugu.www.pcbonlinev2.service.SmsSendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 消息推送  消费者
 */
@Slf4j
@Component
public class NotificationReceiver {

    @Autowired
    private MailSendService mailSendService;

    @Autowired
    private SmsSendService smsSendService;

    /**
     * 审核邮件队列
     *
     * @param msg
     */
    @RabbitListener(queues = "pcbonline.email.audit")
    public void onEmailAuditMsgHandle(NotificationMessage msg) {
        log.info("queue pcbonline.email.audit received msg:[{}] ", msg);
        mailSendService.sendAuditMsgEmail(msg.getReceiver(), msg.getTemplateName(), msg.getData());
    }

    /**
     * 支付邮件队列
     *
     * @param msg
     */
    @RabbitListener(queues = "pcbonline.email.pay")
    public void onEmailPayMsgHandle(NotificationMessage msg) {
        log.info("queue pcbonline.email.pay received msg:[{}] ", msg);
        mailSendService.sendPayMsgEmail(msg.getReceiver(), msg.getTemplateName(), msg.getData());
    }

    /**
     * 审核短信队列
     *
     * @param msg
     */
    @RabbitListener(queues = "pcbonline.sms.audit")
    public void onSmsAuditMsgHandle(NotificationMessage msg) {
        log.info("queue pcbonline.sms.audit received msg:[{}]", msg);
        smsSendService.sendAuditSmsMsg(msg.getReceiver(), createOrderMsg((String) msg.getData().get("pns")));
    }

    /**
     * 支付短信队列
     *
     * @param msg
     */
    @RabbitListener(queues = "pcbonline.sms.pay")
    public void onSmsPayMsgHandle(NotificationMessage msg) {
        log.info("queue pcbonline.sms.pay received msg:[{}]", msg);
        Map<String, Object> data = msg.getData();
        smsSendService.sendPaySmsMsg(msg.getReceiver(), createPayMsg((String) data.get("name"), (String) data.get("pns"), (BigDecimal) data.get("total")));
    }


    private String createPayMsg(String name, String pns, BigDecimal total) {
        return new Gson().toJson(PaySmsVo.builder().bn(name).pn(pns).total(total).build());
    }


    private String createOrderMsg(String pns) {
        return new Gson().toJson(AuditSmsVo.builder().orderType("PCB").orderNo(pns).build());
    }
}
