package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ReceiverAddersDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ReceiverAddersQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ReceiverAddersDO;


import java.util.List;

/**
 * 收货地址
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
public interface ReceiverAddersService extends IService<ReceiverAddersDO> {

    PageResult<List<ReceiverAddersDTO>> queryPage(PageQuery<ReceiverAddersQueryDTO, ReceiverAddersDO> params);

}

