package com.jugu.www.pcbonlinev2.aspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import com.jugu.www.pcbonlinev2.domain.common.Result;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Aspect
@Component
public class EmailSmsSendAspect {

    private final SmsSendService smsSendService;

    private final BusinessUserService businessUserService;

    private final MailSendService mailSendService;


    @Autowired
    public EmailSmsSendAspect(SmsSendService smsSendService, BusinessUserService businessUserService, MailSendService mailSendService) {
        this.smsSendService = smsSendService;
        this.businessUserService = businessUserService;
        this.mailSendService = mailSendService;
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
            sendMsg(r);
            sendEmail(r);
        }else {
            log.info("执行结果失败发送信息");
        }
        return result;
    }

    private void sendEmail(Result r) {
        log.info("开始发送邮箱通知消息");
        mailSendService.sendAllMsgEmail(r.getMsgType(),r.getPns(),r.getTotal());
    }

    private void sendMsg(Result r) {

        log.info("开始发送短信,参数->[{}]",r.toString());
        Integer businessId = ThreadSessionLocal.getUserInfo().getBusinessId();
        //获取跟单员信息
        BusinessUserDO businessUserDO = businessUserService.getOne(new QueryWrapper<BusinessUserDO>().eq("business_id",businessId));
        switch (r.getMsgType()){
            case 1:
                if (businessUserDO != null && businessUserDO.getTelephone() != null) {
                    log.info("发送审核短信，接收人:[{}]",businessUserDO.getTelephone());
                    smsSendService.sendAuditSmsMsg(businessUserDO.getTelephone(),createOrderMsg(r.getPns()));
                }
                break;
            case 2:
                if (businessUserDO != null && businessUserDO.getTelephone() != null && businessUserDO.getName() != null) {
                    log.info("发送支付通知短信,接收人:[{}]",businessUserDO.getTelephone());
                    smsSendService.sendPaySmsMsg(businessUserDO.getTelephone(), createPayMsg(businessUserDO.getName(), r.getPns(), r.getTotal()));
                }
                break;
        }
    }

    private String createPayMsg(String name, String pns, BigDecimal total) {
        return new Gson().toJson(PaySmsVo.builder().bn(name).pn(pns).total(total).build());
    }


    private String createOrderMsg(String pns) {
        return new Gson().toJson(AuditSmsVo.builder().orderType("PCB").orderNo(pns).build());
    }
}
