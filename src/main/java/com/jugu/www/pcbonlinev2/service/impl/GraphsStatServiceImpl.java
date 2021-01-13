package com.jugu.www.pcbonlinev2.service.impl;

import com.jugu.www.pcbonlinev2.domain.dto.OrderAmountDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserDetailsDTO;
import com.jugu.www.pcbonlinev2.mapper.OrderMapper;
import com.jugu.www.pcbonlinev2.service.GraphsStatService;
import com.jugu.www.pcbonlinev2.utils.ThreadSessionLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("graphsStatService")
public class GraphsStatServiceImpl implements GraphsStatService {

    private final OrderMapper orderMapper;

    @Autowired
    public GraphsStatServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public List<OrderAmountDTO> statOrderSaleByCurrYear(String currYear) {
        UserDetailsDTO userInfo = ThreadSessionLocal.getUserInfo();
        return orderMapper.statOrderSale(currYear,userInfo.getId());
    }
}
