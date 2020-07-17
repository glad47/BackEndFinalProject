package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MaterialDO;


import java.util.List;

/**
 * 材料表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface MaterialService extends IService<MaterialDO> {

    PageResult<List<MaterialDTO>> queryPage(PageQuery<MaterialQueryDTO, MaterialDO> params);

}

