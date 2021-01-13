package com.jugu.www.pcbonlinev2.service;

import com.jugu.www.pcbonlinev2.domain.dto.OrderAmountDTO;

import java.util.List;

/**
 * 图形统计
 */
public interface GraphsStatService {

    List<OrderAmountDTO> statOrderSaleByCurrYear(String currYear);
}
