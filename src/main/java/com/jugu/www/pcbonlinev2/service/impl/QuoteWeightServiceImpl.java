package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteWeightDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteWeightQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteWeightDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteWeightMapper;
import com.jugu.www.pcbonlinev2.service.QuoteWeightService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteWeightService")
public class QuoteWeightServiceImpl extends ServiceImpl<QuoteWeightMapper, QuoteWeightDO> implements QuoteWeightService {

    @Autowired
    private QuoteWeightMapper quoteWeightMapper;

    @Override
    public  PageResult<List<QuoteWeightDTO>> queryPage(PageQuery<QuoteWeightQueryDTO, QuoteWeightDO> params) {
        ValidatorUtil.validate(params);

        QuoteWeightDO query = new QuoteWeightDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteWeightDO> quoteWeightDOPage = quoteWeightMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteWeightDTO> quoteWeightDTOList  = Optional.ofNullable(quoteWeightDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteWeightDO -> {
                    QuoteWeightDTO quoteWeightDTO = new QuoteWeightDTO();
                    BeanUtils.copyProperties(QuoteWeightDO, quoteWeightDTO);
                    return quoteWeightDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteWeightDOPage,quoteWeightDTOList);
    }

}