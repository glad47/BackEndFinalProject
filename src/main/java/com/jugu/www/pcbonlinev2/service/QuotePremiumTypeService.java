package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumTypeDO;


import java.util.List;

/**
 * 报价加价类型配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuotePremiumTypeService extends IService<QuotePremiumTypeDO> {

    PageResult<List<QuotePremiumTypeDTO>> queryPage(PageQuery<QuotePremiumTypeQueryDTO, QuotePremiumTypeDO> params);

}

