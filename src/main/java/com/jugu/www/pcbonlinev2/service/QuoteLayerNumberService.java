package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteLayerNumberDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteLayerNumberQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteLayerNumberDO;


import java.util.List;

/**
 * 报价层数配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteLayerNumberService extends IService<QuoteLayerNumberDO> {

    PageResult<List<QuoteLayerNumberDTO>> queryPage(PageQuery<QuoteLayerNumberQueryDTO, QuoteLayerNumberDO> params);

}

