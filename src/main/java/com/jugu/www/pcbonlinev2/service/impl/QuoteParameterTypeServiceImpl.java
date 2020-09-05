package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteParameterTypeDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteParameterTypeMapper;
import com.jugu.www.pcbonlinev2.service.QuoteParameterTypeService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteParameterTypeService")
public class QuoteParameterTypeServiceImpl extends ServiceImpl<QuoteParameterTypeMapper, QuoteParameterTypeDO> implements QuoteParameterTypeService {

    @Autowired
    private QuoteParameterTypeMapper quoteParameterTypeMapper;

    @Override
    public  PageResult<List<QuoteParameterTypeDTO>> queryPage(PageQuery<QuoteParameterTypeQueryDTO, QuoteParameterTypeDO> params) {
        ValidatorUtil.validate(params);

        QuoteParameterTypeDO query = new QuoteParameterTypeDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteParameterTypeDO> quoteParameterTypeDOPage = quoteParameterTypeMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteParameterTypeDTO> quoteParameterTypeDTOList  = Optional.ofNullable(quoteParameterTypeDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteParameterTypeDO -> {
                    QuoteParameterTypeDTO quoteParameterTypeDTO = new QuoteParameterTypeDTO();
                    BeanUtils.copyProperties(QuoteParameterTypeDO, quoteParameterTypeDTO);
                    return quoteParameterTypeDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteParameterTypeDOPage,quoteParameterTypeDTOList);
    }

}