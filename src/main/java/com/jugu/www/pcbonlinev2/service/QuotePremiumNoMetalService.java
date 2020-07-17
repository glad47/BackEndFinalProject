package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumNoMetalDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumNoMetalQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumNoMetalDO;


import java.util.List;

/**
 * 报价非金属配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuotePremiumNoMetalService extends IService<QuotePremiumNoMetalDO> {

    PageResult<List<QuotePremiumNoMetalDTO>> queryPage(PageQuery<QuotePremiumNoMetalQueryDTO, QuotePremiumNoMetalDO> params);

}

