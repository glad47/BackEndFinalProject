package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteUrgentTypeDO;


import java.util.List;

/**
 * 报价加急类型配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteUrgentTypeService extends IService<QuoteUrgentTypeDO> {

    PageResult<List<QuoteUrgentTypeDTO>> queryPage(PageQuery<QuoteUrgentTypeQueryDTO, QuoteUrgentTypeDO> params);

}

