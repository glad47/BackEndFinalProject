package com.jugu.www.pcbonlinev2.service.impl;

import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import com.jugu.www.pcbonlinev2.service.MailSendService;
import com.jugu.www.pcbonlinev2.utils.JwtTokenUtil;
import com.jugu.www.pcbonlinev2.utils.MessageContentBuilder;
import com.jugu.www.pcbonlinev2.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service("mailSendService")
public class MailSendServiceImpl implements MailSendService {
    private static final String encoding = "UTF-8";
    private static final boolean ISHTML = true;
    private static final boolean ISMULTIPART = true;

    private final MessageContentBuilder contentBuilder;

    private final JavaMailSender mailSender;

    private final JwtTokenUtil jwtTokenUtil;

    private final RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String from;

    @Value("${pcbonline.send-notice-base-url}")
    private String sendNoticeBaseUrl;

    /**
     * 默认询盘接收邮箱
     */
    private final String DEFAULT_CONTACT_EMAIL = "info@pcbonline.com";

    @Autowired
    public MailSendServiceImpl(MessageContentBuilder contentBuilder, JavaMailSender mailSender, JwtTokenUtil jwtTokenUtil, RedisUtil redisUtil) {
        this.contentBuilder = contentBuilder;
        this.mailSender = mailSender;
        this.jwtTokenUtil = jwtTokenUtil;
        this.redisUtil = redisUtil;
    }


    /**
     * 发送带模板的邮件
     *
     * @param recipients   收件人有效
     * @param subject      主题
     * @param templateName 模版名称
     * @param datas        模版里的数据（键值对）
     * @param attachments  附加 （可为空）
     */
    private void sendMail(String[] recipients, String subject, String templateName, Map<String, Object> datas, String[] attachments) {
        try {
            MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
                MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, ISMULTIPART, encoding);
                composeMessageHeader(recipients, subject, attachments, messageHelper);
                messageHelper.setText(contentBuilder.buildMessage(templateName, datas), ISHTML);
            };

            mailSender.send(mimeMessagePreparator);
            log.info("发送邮件完成");
        } catch (Exception e){
            log.error("发送邮件出错",e);
        }

    }

    private void composeMessageHeader(String[] recipients, String subject, String[] attachments, MimeMessageHelper messageHelper) throws MessagingException {
        messageHelper.setFrom(from);
        messageHelper.setTo(recipients);
        messageHelper.setSubject(subject);
        if (attachments != null) {
            for (String filename : attachments) {
                FileSystemResource file = new FileSystemResource(filename);
                messageHelper.addAttachment(Objects.requireNonNull(file.getFilename()), file);
            }
        }
    }

    public void sendMailText(String[] recipients, String subject, String message, String[] attachments) {

    }

    public void sendMailWithInlineImage(String[] recipients, String subject, String templateName, Map<String, Object> datas, String[] attachments, String imageResourceName, String imageFileName) {

    }

    public void sendMailWithInlineImage(String[] recipients, String subject, String templateName, Map<String, Object> datas, String[] attachments, String imageResourceName, byte[] imageBytes, String imageContentType) {

    }

    /**
     * 异步发送带模板的邮件
     *
     * @param recipients   收件人有效
     * @param subject      主题
     * @param templateName 模版名称
     * @param data        模版里的数据（键值对）
     * @param attachments  附加 （可为空）
     */
    public void asyncSendMail(String[] recipients, String subject, String templateName, Map<String, Object> data, String[] attachments) {
        sendMail(recipients,subject,templateName,data,attachments);
    }

    @Async("msgSendServerExecutor")
    @Override
    public void asyncSendRegisterMail(String username, String form) {
        log.info("异步发送注册邮件，邮箱：【{}】",username);
        Map<String, Object> data = createTokenEmailSendData(username,form,sendNoticeBaseUrl+"/user/activeAccount");
        sendMail(new String[]{username},"PCBONLINE Account Verification","mail-notice-template-registration",data,null);

    }

    private Map<String, Object> createTokenEmailSendData(String username,String form,Object url) {
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUsername(username);

        Map<String,Object> data = new HashMap<>();
        String token = jwtTokenUtil.generateToken(userDetailsDTO);
        if (form != null){
            data.put("url",url+"?token="+token+"&from="+form);
        }else{
            data.put("url",url+"?token="+token);
        }

        return data;
    }

    @Async("msgSendServerExecutor")
    @Override
    public void asyncSendPasswordResetMail(String email) {
        log.info("异步发送重置密码邮箱,【{}】", email);
        Map<String, Object> tokenEmailSendData = createTokenEmailSendData(email, null,sendNoticeBaseUrl+"/user/resetPassword");
        sendMail(new String[]{email},"PCBONLINE Account Change Password","mail-template-updatepwd",tokenEmailSendData,null);
    }

    @Async("msgSendServerExecutor")
    @Override
    public void asyncSendContactEmail(String email, String name, String msg) {
        log.info("发送询盘邮件");
        Map<String,Object> data = new HashMap<>();
        data.put("data","收到新的联系信息，Name: " + name + ",Email: " + email+", msg:"+ msg);
        sendMail(new String[]{DEFAULT_CONTACT_EMAIL},"PCBONLINE 首页询盘通知","mail-template-contact",data,null);
    }

    @Async("msgSendServerExecutor")
    @Override
    public void asyncSendReviewEmail(String email, String name, String msg) {
        log.info("发送反馈邮件");
        Map<String,Object> data = new HashMap<>();
        data.put("data","收到新的联系信息，Name: " + name + ",Email: " + email+", msg:"+ msg);
        sendMail(new String[]{DEFAULT_CONTACT_EMAIL},"PCBONINE Feedback页面反馈通知","mail-template-feedback-review",data,null);
    }

    @Override
    public void sendAllMsgEmail(int msgType, String pns, BigDecimal total) {
        Map<String, Object> data = new HashMap<>();
        data.put("msgType",msgType);
        data.put("pns",pns);
        data.put("total",total);

        //获取所有接收邮件邮箱
        Set<Object> emailReceivers = redisUtil.sGet("NotifyEmailList-Set");
        if (emailReceivers.size() == 0){
            log.info("所有接收邮件邮箱为空，不发送邮箱!!");
            return;
        }
        String[] emails = emailReceivers.toArray(new String[]{});
        sendMail(emails,"PCBONLINE 审核/付款通知邮件","mail-notice-template-order-pcb",data,null);
    }

    @Override
    public void sendAuditMsgEmail(String receiver, String templateName, Map data) {
        sendMail(new String[]{receiver},"PCBONLINE 订单审核通知", templateName, data, null);
    }

    @Override
    public void sendPayMsgEmail(String receiver, String templateName, Map<String, Object> data) {
        sendMail(new String[]{receiver}, "PCBONLINE 订单付款通知邮件", templateName,data,null);
    }
}
