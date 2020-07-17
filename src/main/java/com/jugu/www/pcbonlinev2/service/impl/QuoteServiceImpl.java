package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteMapper;
import com.jugu.www.pcbonlinev2.service.QuoteService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteService")
public class QuoteServiceImpl extends ServiceImpl<QuoteMapper, QuoteDO> implements QuoteService {

    @Autowired
    private QuoteMapper quoteMapper;

    @Override
    public  PageResult<List<QuoteDTO>> queryPage(PageQuery<QuoteQueryDTO, QuoteDO> params) {
        ValidatorUtil.validate(params);

        QuoteDO query = new QuoteDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteDO> quoteDOPage = quoteMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteDTO> quoteDTOList  = Optional.ofNullable(quoteDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteDO -> {
                    QuoteDTO quoteDTO = new QuoteDTO();
                    BeanUtils.copyProperties(QuoteDO, quoteDTO);
                    return quoteDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteDOPage,quoteDTOList);
    }

}