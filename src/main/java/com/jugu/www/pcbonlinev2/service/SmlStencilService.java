package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.SmlStencilDTO;
import com.jugu.www.pcbonlinev2.domain.dto.SmlStencilQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.SmlStencilDO;


import java.util.List;

/**
 * 钢网订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
public interface SmlStencilService extends IService<SmlStencilDO> {

    PageResult<List<SmlStencilDTO>> queryPage(PageQuery<SmlStencilQueryDTO, SmlStencilDO> params);

}

