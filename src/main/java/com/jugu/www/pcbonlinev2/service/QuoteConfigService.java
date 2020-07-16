package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteConfigDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteConfigQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteConfigDO;


import java.util.List;

/**
 * 报价公式表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 17:47:29
 */
public interface QuoteConfigService extends IService<QuoteConfigDO> {

    PageResult<List<QuoteConfigDTO>> queryPage(PageQuery<QuoteConfigQueryDTO, QuoteConfigDO> params);

}

