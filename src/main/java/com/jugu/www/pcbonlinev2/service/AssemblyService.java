package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.AssemblyDTO;
import com.jugu.www.pcbonlinev2.domain.dto.AssemblyQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.AssemblyDO;


import java.util.List;

/**
 * 切片订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
public interface AssemblyService extends IService<AssemblyDO> {

    PageResult<List<AssemblyDTO>> queryPage(PageQuery<AssemblyQueryDTO, AssemblyDO> params);

}

