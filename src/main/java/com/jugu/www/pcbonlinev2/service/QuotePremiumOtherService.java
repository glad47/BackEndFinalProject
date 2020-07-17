package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumOtherDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumOtherQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumOtherDO;


import java.util.List;

/**
 * 报价其他配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuotePremiumOtherService extends IService<QuotePremiumOtherDO> {

    PageResult<List<QuotePremiumOtherDTO>> queryPage(PageQuery<QuotePremiumOtherQueryDTO, QuotePremiumOtherDO> params);

}

