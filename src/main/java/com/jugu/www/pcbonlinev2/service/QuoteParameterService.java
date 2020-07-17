package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteParameterDO;


import java.util.List;

/**
 * 报价参数名配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteParameterService extends IService<QuoteParameterDO> {

    PageResult<List<QuoteParameterDTO>> queryPage(PageQuery<QuoteParameterQueryDTO, QuoteParameterDO> params);

}

