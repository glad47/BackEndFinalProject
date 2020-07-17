package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAreaParameterDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAreaParameterQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteAreaParameterDO;


import java.util.List;

/**
 * 报价参数面积配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteAreaParameterService extends IService<QuoteAreaParameterDO> {

    PageResult<List<QuoteAreaParameterDTO>> queryPage(PageQuery<QuoteAreaParameterQueryDTO, QuoteAreaParameterDO> params);

}

