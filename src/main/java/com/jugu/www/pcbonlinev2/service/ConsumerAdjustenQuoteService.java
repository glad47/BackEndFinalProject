package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ConsumerAdjustenQuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ConsumerAdjustenQuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ConsumerAdjustenQuoteDO;


import java.util.List;

/**
 * 客户调整价格报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface ConsumerAdjustenQuoteService extends IService<ConsumerAdjustenQuoteDO> {

    PageResult<List<ConsumerAdjustenQuoteDTO>> queryPage(PageQuery<ConsumerAdjustenQuoteQueryDTO, ConsumerAdjustenQuoteDO> params);

}

