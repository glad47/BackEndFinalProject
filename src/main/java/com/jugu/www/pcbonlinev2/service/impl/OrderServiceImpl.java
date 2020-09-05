package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.OrderDTO;
import com.jugu.www.pcbonlinev2.domain.dto.OrderQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.OrderDO;
import com.jugu.www.pcbonlinev2.mapper.OrderMapper;
import com.jugu.www.pcbonlinev2.service.OrderService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderDO> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public  PageResult<List<OrderDTO>> queryPage(PageQuery<OrderQueryDTO, OrderDO> params) {
        ValidatorUtil.validate(params);

        OrderDO query = new OrderDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<OrderDO> orderDOPage = orderMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<OrderDTO> orderDTOList  = Optional.ofNullable(orderDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(OrderDO -> {
                    OrderDTO orderDTO = new OrderDTO();
                    BeanUtils.copyProperties(OrderDO, orderDTO);
                    return orderDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(orderDOPage,orderDTOList);
    }

}