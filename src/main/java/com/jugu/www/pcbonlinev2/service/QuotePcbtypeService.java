package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePcbtypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePcbtypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePcbtypeDO;


import java.util.List;

/**
 * 报价pcb基材类型配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface QuotePcbtypeService extends IService<QuotePcbtypeDO> {

    PageResult<List<QuotePcbtypeDTO>> queryPage(PageQuery<QuotePcbtypeQueryDTO, QuotePcbtypeDO> params);

}

