package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteWeightDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteWeightQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteWeightDO;


import java.util.List;

/**
 * 报价重量配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteWeightService extends IService<QuoteWeightDO> {

    PageResult<List<QuoteWeightDTO>> queryPage(PageQuery<QuoteWeightQueryDTO, QuoteWeightDO> params);

}

