package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.RetrievePwdDTO;
import com.jugu.www.pcbonlinev2.domain.dto.RetrievePwdQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.RetrievePwdDO;


import java.util.List;

/**
 * 取回密码
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:38
 */
public interface RetrievePwdService extends IService<RetrievePwdDO> {

    PageResult<List<RetrievePwdDTO>> queryPage(PageQuery<RetrievePwdQueryDTO, RetrievePwdDO> params);

}

