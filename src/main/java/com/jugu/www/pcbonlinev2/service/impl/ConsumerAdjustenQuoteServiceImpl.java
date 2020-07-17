package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ConsumerAdjustenQuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ConsumerAdjustenQuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ConsumerAdjustenQuoteDO;
import com.jugu.www.pcbonlinev2.mapper.ConsumerAdjustenQuoteMapper;
import com.jugu.www.pcbonlinev2.service.ConsumerAdjustenQuoteService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("consumerAdjustenQuoteService")
public class ConsumerAdjustenQuoteServiceImpl extends ServiceImpl<ConsumerAdjustenQuoteMapper, ConsumerAdjustenQuoteDO> implements ConsumerAdjustenQuoteService {

    @Autowired
    private ConsumerAdjustenQuoteMapper consumerAdjustenQuoteMapper;

    @Override
    public  PageResult<List<ConsumerAdjustenQuoteDTO>> queryPage(PageQuery<ConsumerAdjustenQuoteQueryDTO, ConsumerAdjustenQuoteDO> params) {
        ValidatorUtil.validate(params);

        ConsumerAdjustenQuoteDO query = new ConsumerAdjustenQuoteDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<ConsumerAdjustenQuoteDO> consumerAdjustenQuoteDOPage = consumerAdjustenQuoteMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<ConsumerAdjustenQuoteDTO> consumerAdjustenQuoteDTOList  = Optional.ofNullable(consumerAdjustenQuoteDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ConsumerAdjustenQuoteDO -> {
                    ConsumerAdjustenQuoteDTO consumerAdjustenQuoteDTO = new ConsumerAdjustenQuoteDTO();
                    BeanUtils.copyProperties(ConsumerAdjustenQuoteDO, consumerAdjustenQuoteDTO);
                    return consumerAdjustenQuoteDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(consumerAdjustenQuoteDOPage,consumerAdjustenQuoteDTOList);
    }

}