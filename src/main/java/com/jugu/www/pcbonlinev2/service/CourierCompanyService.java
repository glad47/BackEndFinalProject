package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCompanyDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCompanyQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierCompanyDO;


import java.util.List;

/**
 * 快递公司表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface CourierCompanyService extends IService<CourierCompanyDO> {

    PageResult<List<CourierCompanyDTO>> queryPage(PageQuery<CourierCompanyQueryDTO, CourierCompanyDO> params);

}

