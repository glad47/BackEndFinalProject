package com.jugu.www.pcbonlinev2.state.core;

import com.jugu.www.pcbonlinev2.PcbOnlineV2Application;
import com.jugu.www.pcbonlinev2.state.constant.OrderStatusEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = PcbOnlineV2Application.class)
class OrderStateManagerTest {

    @Autowired
    private OrderStateManager orderStateManager;

    @Test
    public void createTest() {
        Assert.assertEquals(2,orderStateManager.handleEvent(1, OrderStatusEnum.CREATE_EVENT,1));
    }
}