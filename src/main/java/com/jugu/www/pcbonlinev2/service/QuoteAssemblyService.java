package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAssemblyDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAssemblyQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteAssemblyDO;


import java.util.List;

/**
 * 
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteAssemblyService extends IService<QuoteAssemblyDO> {

    PageResult<List<QuoteAssemblyDTO>> queryPage(PageQuery<QuoteAssemblyQueryDTO, QuoteAssemblyDO> params);

}

