package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteExchangeRateDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteExchangeRateQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteExchangeRateDO;


import java.util.List;

/**
 * 汇率配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteExchangeRateService extends IService<QuoteExchangeRateDO> {

    PageResult<List<QuoteExchangeRateDTO>> queryPage(PageQuery<QuoteExchangeRateQueryDTO, QuoteExchangeRateDO> params);

}

