package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CityDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CityQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CityDO;


import java.util.List;

/**
 * 城市表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface CityService extends IService<CityDO> {

    PageResult<List<CityDTO>> queryPage(PageQuery<CityQueryDTO, CityDO> params);

}

