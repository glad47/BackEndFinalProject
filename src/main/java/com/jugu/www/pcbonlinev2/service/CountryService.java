package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CountryDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CountryQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CountryDO;


import java.util.List;

/**
 * 
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
public interface CountryService extends IService<CountryDO> {

    PageResult<List<CountryDTO>> queryPage(PageQuery<CountryQueryDTO, CountryDO> params);

}

