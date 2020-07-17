package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCountryPartitionDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCountryPartitionQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierCountryPartitionDO;


import java.util.List;

/**
 * 快递国家分区表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface CourierCountryPartitionService extends IService<CourierCountryPartitionDO> {

    PageResult<List<CourierCountryPartitionDTO>> queryPage(PageQuery<CourierCountryPartitionQueryDTO, CourierCountryPartitionDO> params);

}

