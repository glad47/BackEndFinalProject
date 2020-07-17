package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteDO;


import java.util.List;

/**
 * 报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteService extends IService<QuoteDO> {

    PageResult<List<QuoteDTO>> queryPage(PageQuery<QuoteQueryDTO, QuoteDO> params);

}

