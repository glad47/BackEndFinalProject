package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.OrderDTO;
import com.jugu.www.pcbonlinev2.domain.dto.OrderQueryDTO;
import com.jugu.www.pcbonlinev2.domain.dto.OrderSaveDTO;
import com.jugu.www.pcbonlinev2.domain.dto.PaymentParameterDTO;
import com.jugu.www.pcbonlinev2.domain.entity.OrderDO;


import java.util.List;

/**
 * 订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface OrderService extends IService<OrderDO> {

    PageResult<List<OrderDTO>> queryPage(PageQuery<OrderQueryDTO, OrderDO> params);

    boolean saveOrder(OrderSaveDTO orderSaveDTO);

    boolean createOrder(PaymentParameterDTO paymentParameterDTO);

    String createOrderNo();
}

