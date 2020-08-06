package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.PcbOnlineV2Application;
import com.jugu.www.pcbonlinev2.state.constant.Event;
import com.jugu.www.pcbonlinev2.state.constant.State;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PcbOnlineV2Application.class)
@SpringBootTest
class OrderStateManagerTest {

    @Autowired
    private OrderStateManager orderStateManager;

    @Test
    public void createTest() {
        Assert.assertEquals(2,orderStateManager.handleEvent(1, State.ORDER_FINISHED,1));
    }

    @Test
    public void createPostTest(){
        Assert.assertEquals(2, orderStateManager.post(1, Event.CREATE_EVENT,State.TEMPORARY_ORDER));
    }
}