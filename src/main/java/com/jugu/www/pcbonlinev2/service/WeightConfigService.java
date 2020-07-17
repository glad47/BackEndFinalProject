package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.WeightConfigDTO;
import com.jugu.www.pcbonlinev2.domain.dto.WeightConfigQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.WeightConfigDO;


import java.util.List;

/**
 * 重量公式表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface WeightConfigService extends IService<WeightConfigDO> {

    PageResult<List<WeightConfigDTO>> queryPage(PageQuery<WeightConfigQueryDTO, WeightConfigDO> params);

}

