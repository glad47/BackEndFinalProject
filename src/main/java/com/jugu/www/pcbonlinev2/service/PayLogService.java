package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.PayLogDTO;
import com.jugu.www.pcbonlinev2.domain.dto.PayLogQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.PayLogDO;


import java.util.List;

/**
 * 支付日志
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface PayLogService extends IService<PayLogDO> {

    PageResult<List<PayLogDTO>> queryPage(PageQuery<PayLogQueryDTO, PayLogDO> params);

}

