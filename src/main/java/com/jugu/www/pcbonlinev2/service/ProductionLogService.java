package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ProductionLogDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ProductionLogQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ProductionLogDO;


import java.util.List;

/**
 * 生产日志
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-29 15:45:00
 */
public interface ProductionLogService extends IService<ProductionLogDO> {

    PageResult<List<ProductionLogDTO>> queryPage(PageQuery<ProductionLogQueryDTO, ProductionLogDO> params);

}

