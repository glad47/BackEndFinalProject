package com.jugu.www.pcbonlinev2.mapper;

import com.jugu.www.pcbonlinev2.domain.dto.OrderAmountDTO;
import com.jugu.www.pcbonlinev2.domain.entity.OrderDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Mapper
public interface OrderMapper extends BaseMapper<OrderDO> {

    List<OrderAmountDTO> statOrderSale(@Param("currYear") String currYear, @Param("uid") Integer id);
}
