package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.PcbOnlineV2Application;
import com.jugu.www.pcbonlinev2.service.MailSendService;
import com.jugu.www.pcbonlinev2.state.constant.Event;
import com.jugu.www.pcbonlinev2.state.constant.State;
import com.jugu.www.pcbonlinev2.utils.RedisUtil;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PcbOnlineV2Application.class)
@SpringBootTest
class OrderStateManagerTest {

    @Autowired
    private OrderStateManager orderStateManager;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private MailSendService mailSendService;

    @Test
    public void createTest() {
        Assert.assertEquals(2,orderStateManager.handleEvent(1, State.ORDER_FINISHED,1));
    }

    @Test
    public void createPostTest(){
        Assert.assertEquals(2, orderStateManager.post(1, Event.CREATE_EVENT,State.TEMPORARY_ORDER));
    }

    @Test
    public void redisTest(){
        Set<Object> objects = redisUtil.sGet("NotifyMobileList-Set");
        //System.out.println(Arrays.toString(objects.toArray()));
        //System.out.println(objects.toString());
        System.out.println(objects.stream().map(Object::toString).collect(Collectors.joining(",")));
        List<Object> list = new ArrayList<>(objects);
        System.out.println(list.stream().map(Object::toString).collect(Collectors.joining(",")));
        Set<Object> emailReceivers = redisUtil.sGet("NotifyEmailList-Set");
        String[] emails = emailReceivers.toArray(new String[]{});
        System.out.println(Arrays.toString(emails));
    }


    @Test
    public void emailSendTest(){
        mailSendService.sendAllMsgEmail(2,"pk22222 pl22222 p22223444",new BigDecimal(90000));
    }

//    @Test
//    public void wsTest(){
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//
//        Client client = dcf.createClient("https://wsbeta.fedex.com:443/web-services");
//
//        try {
//            Object[] invoke = client.invoke("CodReturnReferenceIndicatorType");
//
//            System.out.println("返回数据:" + invoke.toString() );
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//
//    }
}