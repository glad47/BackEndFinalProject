package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.GraphsDTO;
import com.jugu.www.pcbonlinev2.domain.dto.GraphsQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.GraphsDO;


import java.util.List;

/**
 * 跟单员统计表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface GraphsService extends IService<GraphsDO> {

    PageResult<List<GraphsDTO>> queryPage(PageQuery<GraphsQueryDTO, GraphsDO> params);

}

