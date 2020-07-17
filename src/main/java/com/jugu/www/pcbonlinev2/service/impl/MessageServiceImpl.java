package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MessageDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MessageQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MessageDO;
import com.jugu.www.pcbonlinev2.mapper.MessageMapper;
import com.jugu.www.pcbonlinev2.service.MessageService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("messageService")
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageDO> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public  PageResult<List<MessageDTO>> queryPage(PageQuery<MessageQueryDTO, MessageDO> params) {
        ValidatorUtil.validate(params);

        MessageDO query = new MessageDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<MessageDO> messageDOPage = messageMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<MessageDTO> messageDTOList  = Optional.ofNullable(messageDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(MessageDO -> {
                    MessageDTO messageDTO = new MessageDTO();
                    BeanUtils.copyProperties(MessageDO, messageDTO);
                    return messageDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(messageDOPage,messageDTOList);
    }

}