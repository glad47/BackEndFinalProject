package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumMetalDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumMetalQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumMetalDO;


import java.util.List;

/**
 * 报价金属配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuotePremiumMetalService extends IService<QuotePremiumMetalDO> {

    PageResult<List<QuotePremiumMetalDTO>> queryPage(PageQuery<QuotePremiumMetalQueryDTO, QuotePremiumMetalDO> params);

}

