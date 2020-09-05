package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAreaParameterDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAreaParameterQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteAreaParameterDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteAreaParameterMapper;
import com.jugu.www.pcbonlinev2.service.QuoteAreaParameterService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteAreaParameterService")
public class QuoteAreaParameterServiceImpl extends ServiceImpl<QuoteAreaParameterMapper, QuoteAreaParameterDO> implements QuoteAreaParameterService {

    @Autowired
    private QuoteAreaParameterMapper quoteAreaParameterMapper;

    @Override
    public  PageResult<List<QuoteAreaParameterDTO>> queryPage(PageQuery<QuoteAreaParameterQueryDTO, QuoteAreaParameterDO> params) {
        ValidatorUtil.validate(params);

        QuoteAreaParameterDO query = new QuoteAreaParameterDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteAreaParameterDO> quoteAreaParameterDOPage = quoteAreaParameterMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteAreaParameterDTO> quoteAreaParameterDTOList  = Optional.ofNullable(quoteAreaParameterDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteAreaParameterDO -> {
                    QuoteAreaParameterDTO quoteAreaParameterDTO = new QuoteAreaParameterDTO();
                    BeanUtils.copyProperties(QuoteAreaParameterDO, quoteAreaParameterDTO);
                    return quoteAreaParameterDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteAreaParameterDOPage,quoteAreaParameterDTOList);
    }

}