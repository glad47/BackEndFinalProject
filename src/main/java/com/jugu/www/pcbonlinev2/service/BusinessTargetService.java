package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessTargetDTO;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessTargetQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessTargetDO;


import java.util.List;

/**
 * 跟单员目标指标
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface BusinessTargetService extends IService<BusinessTargetDO> {

    PageResult<List<BusinessTargetDTO>> queryPage(PageQuery<BusinessTargetQueryDTO, BusinessTargetDO> params);

}

