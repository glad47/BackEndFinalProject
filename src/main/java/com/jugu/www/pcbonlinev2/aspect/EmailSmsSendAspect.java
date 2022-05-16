package com.jugu.www.pcbonlinev2.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.jugu.www.pcbonlinev2.domain.common.Result;
import com.jugu.www.pcbonlinev2.domain.dto.notification.NotificationMessage;
import com.jugu.www.pcbonlinev2.domain.dto.notification.NotificationQueueEnum;
import com.jugu.www.pcbonlinev2.domain.dto.notification.TemplateNameEnum;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessUserDO;
import com.jugu.www.pcbonlinev2.domain.vo.AuditSmsVo;
import com.jugu.www.pcbonlinev2.domain.vo.PaySmsVo;
import com.jugu.www.pcbonlinev2.service.BusinessUserService;
import com.jugu.www.pcbonlinev2.service.MailSendService;
import com.jugu.www.pcbonlinev2.service.SmsSendService;
import com.jugu.www.pcbonlinev2.utils.ThreadSessionLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class EmailSmsSendAspect {


    private final BusinessUserService businessUserService;

    private final NotificationSender notificationSender;


    @Autowired
    public EmailSmsSendAspect(BusinessUserService businessUserService, NotificationSender notificationSender) {
        this.businessUserService = businessUserService;
        this.notificationSender = notificationSender;
    }

    @Pointcut("@annotation(com.jugu.www.pcbonlinev2.aspect.EmailSmsSend)")
    public void processPointCut() {
    }


    @Around("processPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //执行方法
        Object result = point.proceed();

        //发送消息
        log.info("发送各种消息业务开始执行>>>>>");
        Result r = (Result) result;
        if (r.isSuccess()){
            pushNotification(r); //推送消息
        }else {
            log.info("执行结果失败,不发送信息");
        }
        return result;
    }

    private void pushNotification(Result r) {

        Integer businessId = ThreadSessionLocal.getUserInfo().getBusinessId();
        //获取跟单员信息
        BusinessUserDO businessUserDO = businessUserService.getOne(new QueryWrapper<BusinessUserDO>().eq("business_id",businessId));
        if (businessUserDO == null) {
            log.info("没有查询出对应跟单员信息，不发送信息");
            return;
        }
        log.info("查询出用户对应跟单员为:[{}]",businessUserDO.toString());

        switch (r.getMsgType()){
            case 1: //审核
                pushAuditNotification(businessUserDO.getTelephone(),businessUserDO.getContactEmail(),r.getTotal(),r.getMsgType(), r.getPns());
                break;
            case 2: //支付
                pushPayNotification(businessUserDO.getTelephone(),businessUserDO.getName(),businessUserDO.getContactEmail(),r.getTotal(),r.getMsgType(),r.getPns());
                break;
        }

    }

    private void pushPayNotification(String telephone, String name, String contactEmail, BigDecimal total, int msgType, String pns) {
        Map<String,Object> data = new HashMap<>();
        data.put("msgType",msgType);
        data.put("pns",pns);
        data.put("total",total);
        data.put("name",name);

        NotificationMessage emailPayMsg = pullMsg(contactEmail, TemplateNameEnum.ORDER_PAY_EMAIL.getName(), data);
        notificationSender.sendMessage(emailPayMsg,NotificationQueueEnum.QUEUE_EMAIL_PAY.getRouteKey());

        NotificationMessage smsPayMsg = pullMsg(conversionTelephone(telephone), TemplateNameEnum.ORDER_PAY_SMS.getName(), data);
        notificationSender.sendMessage(smsPayMsg,NotificationQueueEnum.QUEUE_SMS_PAY.getRouteKey());
    }

    private void pushAuditNotification(String telephone, String contactEmail, BigDecimal total, int msgType, String pns) {
        Map<String, Object> data = new HashMap<>();
        data.put("msgType",msgType);
        data.put("pns",pns);
        data.put("total",total);

        NotificationMessage emailAuditMsg = pullMsg(contactEmail,TemplateNameEnum.ORDER_AUDIT_EMAIL.getName(),data);
        //推送邮件信息到队列
        notificationSender.sendMessage(emailAuditMsg,NotificationQueueEnum.QUEUE_EMAIL_AUDIT.getRouteKey());

        NotificationMessage smsAuditMsg = pullMsg(conversionTelephone(telephone), TemplateNameEnum.ORDER_AUDIT_SMS.getName(), data);
        //推送到SMS信息到队列
        notificationSender.sendMessage(smsAuditMsg,NotificationQueueEnum.QUEUE_SMS_AUDIT.getRouteKey());
    }


    /**
     * 将电话号码 +86 15487897895 转换为1586967895
     * @param telephone
     * @return
     */
    private String conversionTelephone(String telephone) {
        return telephone.replace("+86"," ").trim();
    }


    private NotificationMessage pullMsg(String receiver, String templateName, Map<String, Object> data) {
        NotificationMessage msg = new NotificationMessage();
        msg.setData(data);
        msg.setReceiver(receiver);
        msg.setTemplateName(templateName);
        return msg;
    }

//    private void sendEmail(Result r) {
//        log.info("开始发送邮箱通知消息");
//        mailSendService.sendAllMsgEmail(r.getMsgType(),r.getPns(),r.getTotal());
//    }

//    private void sendMsg(Result r) {
//
//        log.info("开始发送短信,参数->[{}]",r.toString());
//        Integer businessId = ThreadSessionLocal.getUserInfo().getBusinessId();
//        //获取跟单员信息
//        BusinessUserDO businessUserDO = businessUserService.getOne(new QueryWrapper<BusinessUserDO>().eq("business_id",businessId));
//        switch (r.getMsgType()){
//            case 1:
//                if (businessUserDO != null && businessUserDO.getTelephone() != null) {
//                    log.info("发送审核短信，接收人:[{}]",businessUserDO.getTelephone());
//                    smsSendService.sendAuditSmsMsg(businessUserDO.getTelephone(),createOrderMsg(r.getPns()));
//                }
//                break;
//            case 2:
//                if (businessUserDO != null && businessUserDO.getTelephone() != null && businessUserDO.getName() != null) {
//                    log.info("发送支付通知短信,接收人:[{}]",businessUserDO.getTelephone());
//                    smsSendService.sendPaySmsMsg(businessUserDO.getTelephone(), createPayMsg(businessUserDO.getName(), r.getPns(), r.getTotal()));
//                }
//                break;
//        }
//    }

//    private String createPayMsg(String name, String pns, BigDecimal total) {
//        return new Gson().toJson(PaySmsVo.builder().bn(name).pn(pns).total(total).build());
//    }
//
//
//    private String createOrderMsg(String pns) {
//        return new Gson().toJson(AuditSmsVo.builder().orderType("PCB").orderNo(pns).build());
//    }
}
