package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ZanDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ZanQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ZanDO;


import java.util.List;

/**
 * 点赞
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface ZanService extends IService<ZanDO> {

    PageResult<List<ZanDTO>> queryPage(PageQuery<ZanQueryDTO, ZanDO> params);

}

