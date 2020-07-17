package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierQuoteDO;


import java.util.List;

/**
 * 快递报价配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface CourierQuoteService extends IService<CourierQuoteDO> {

    PageResult<List<CourierQuoteDTO>> queryPage(PageQuery<CourierQuoteQueryDTO, CourierQuoteDO> params);

}

