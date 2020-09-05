package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ReceiverAddersDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ReceiverAddersQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ReceiverAddersDO;
import com.jugu.www.pcbonlinev2.mapper.ReceiverAddersMapper;
import com.jugu.www.pcbonlinev2.service.ReceiverAddersService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("receiverAddersService")
public class ReceiverAddersServiceImpl extends ServiceImpl<ReceiverAddersMapper, ReceiverAddersDO> implements ReceiverAddersService {

    @Autowired
    private ReceiverAddersMapper receiverAddersMapper;

    @Override
    public  PageResult<List<ReceiverAddersDTO>> queryPage(PageQuery<ReceiverAddersQueryDTO, ReceiverAddersDO> params) {
        ValidatorUtil.validate(params);

        ReceiverAddersDO query = new ReceiverAddersDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<ReceiverAddersDO> receiverAddersDOPage = receiverAddersMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<ReceiverAddersDTO> receiverAddersDTOList  = Optional.ofNullable(receiverAddersDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ReceiverAddersDO -> {
                    ReceiverAddersDTO receiverAddersDTO = new ReceiverAddersDTO();
                    BeanUtils.copyProperties(ReceiverAddersDO, receiverAddersDTO);
                    return receiverAddersDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(receiverAddersDOPage,receiverAddersDTOList);
    }

}