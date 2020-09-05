package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteExchangeRateDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteExchangeRateQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteExchangeRateDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteExchangeRateMapper;
import com.jugu.www.pcbonlinev2.service.QuoteExchangeRateService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteExchangeRateService")
public class QuoteExchangeRateServiceImpl extends ServiceImpl<QuoteExchangeRateMapper, QuoteExchangeRateDO> implements QuoteExchangeRateService {

    @Autowired
    private QuoteExchangeRateMapper quoteExchangeRateMapper;

    @Override
    public  PageResult<List<QuoteExchangeRateDTO>> queryPage(PageQuery<QuoteExchangeRateQueryDTO, QuoteExchangeRateDO> params) {
        ValidatorUtil.validate(params);

        QuoteExchangeRateDO query = new QuoteExchangeRateDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteExchangeRateDO> quoteExchangeRateDOPage = quoteExchangeRateMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteExchangeRateDTO> quoteExchangeRateDTOList  = Optional.ofNullable(quoteExchangeRateDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteExchangeRateDO -> {
                    QuoteExchangeRateDTO quoteExchangeRateDTO = new QuoteExchangeRateDTO();
                    BeanUtils.copyProperties(QuoteExchangeRateDO, quoteExchangeRateDTO);
                    return quoteExchangeRateDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteExchangeRateDOPage,quoteExchangeRateDTOList);
    }

}