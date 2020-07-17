package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteParameterTypeDO;


import java.util.List;

/**
 * 报价参数类型配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteParameterTypeService extends IService<QuoteParameterTypeDO> {

    PageResult<List<QuoteParameterTypeDTO>> queryPage(PageQuery<QuoteParameterTypeQueryDTO, QuoteParameterTypeDO> params);

}

