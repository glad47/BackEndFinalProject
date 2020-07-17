package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterValueDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterValueQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteParameterValueDO;


import java.util.List;

/**
 * 报价参数值配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteParameterValueService extends IService<QuoteParameterValueDO> {

    PageResult<List<QuoteParameterValueDTO>> queryPage(PageQuery<QuoteParameterValueQueryDTO, QuoteParameterValueDO> params);

}

