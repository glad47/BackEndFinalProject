package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteStencilDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteStencilQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteStencilDO;


import java.util.List;

/**
 * 钢网报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteStencilService extends IService<QuoteStencilDO> {

    PageResult<List<QuoteStencilDTO>> queryPage(PageQuery<QuoteStencilQueryDTO, QuoteStencilDO> params);

}

