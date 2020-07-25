package com.jugu.www.pcbonlinev2.service.impl;

import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import com.jugu.www.pcbonlinev2.service.MailSendService;
import com.jugu.www.pcbonlinev2.utils.JwtTokenUtil;
import com.jugu.www.pcbonlinev2.utils.MessageContentBuilder;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class MailSendServiceImpl implements MailSendService {
    private static final String encoding = "UTF-8";
    private static final boolean ISHTML = true;
    private static final boolean ISMULTIPART = true;

    private final MessageContentBuilder contentBuilder;

    private final JavaMailSender mailSender;

    private final JwtTokenUtil jwtTokenUtil;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    public MailSendServiceImpl(MessageContentBuilder contentBuilder, JavaMailSender mailSender, JwtTokenUtil jwtTokenUtil) {
        this.contentBuilder = contentBuilder;
        this.mailSender = mailSender;
        this.jwtTokenUtil = jwtTokenUtil;
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
    public void asyncSendRegisterMail(String username) {
        log.info("异步发送邮件，邮箱：【{}】",username);
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setUsername(username);

        Map<String,Object> data = new HashMap<>();
        String token = jwtTokenUtil.generateToken(userDetailsDTO);
        data.put("url","http://localhost:8877/api/auth/active/"+token);
        sendMail(new String[]{username},"激活PcbOnLine用户","mail-notice-template-registration",data,null);

    }
}
