package com.jugu.www.pcbonlinev2.service.impl;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.jugu.www.pcbonlinev2.service.SmsSendService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service("smsSendService")
public class SmsSendServiceImpl implements SmsSendService {
    /**
     * 审核
     */
    public final String TEMPLATE_ORDER_AUDIT = "SMS_175425232";
    /**
     * 支付
     */
    public final String TEMPLATE_ORDER_PAY = "SMS_175420523";

    private IAcsClient acsClient;

    public SmsSendServiceImpl(){
        this.acsClient = new DefaultAcsClient(DefaultProfile.getProfile("cn-hangzhou","LTAI4FiBj1iqgpVryjv4pwke","6nQcczoG0P79iVXO1JtgqAmfYpepZc"));
    }

    @Override
    public void sendSms(String phoneNumber, String templateCode, String templateParam) {
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("SignName", "聚谷科技");
        request.putQueryParameter("PhoneNumbers",phoneNumber);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            CommonResponse response = acsClient.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送审核短信
     */
    public void sendAuditSmsMsg(String phoneNumber,String smg){
        this.sendSms(conversionTelephone(phoneNumber),this.TEMPLATE_ORDER_AUDIT,smg);
    }

    /**
     * 发送支付短信
     */
    public void sendPaySmsMsg(String phone, String smg){
        this.sendSms(conversionTelephone(phone),this.TEMPLATE_ORDER_PAY,smg);
    }

    private String conversionTelephone(String phoneNumber) {
        if (!StringUtils.isEmpty(phoneNumber)){
            return phoneNumber.trim().split(" ")[1]+",19925272868";
        }else{
            return "19925272868";
        }
    }
}
