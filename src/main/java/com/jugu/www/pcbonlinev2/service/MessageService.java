package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MessageDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MessageQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MessageDO;


import java.util.List;

/**
 * 信息消息
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface MessageService extends IService<MessageDO> {

    PageResult<List<MessageDTO>> queryPage(PageQuery<MessageQueryDTO, MessageDO> params);

}

