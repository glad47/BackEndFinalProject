package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentBuildDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentBuildQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteUrgentBuildDO;


import java.util.List;

/**
 * 报价加工时间配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuoteUrgentBuildService extends IService<QuoteUrgentBuildDO> {

    PageResult<List<QuoteUrgentBuildDTO>> queryPage(PageQuery<QuoteUrgentBuildQueryDTO, QuoteUrgentBuildDO> params);

}

