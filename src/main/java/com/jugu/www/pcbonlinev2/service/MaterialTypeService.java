package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MaterialTypeDO;


import java.util.List;

/**
 * 材料型号表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface MaterialTypeService extends IService<MaterialTypeDO> {

    PageResult<List<MaterialTypeDTO>> queryPage(PageQuery<MaterialTypeQueryDTO, MaterialTypeDO> params);

}

