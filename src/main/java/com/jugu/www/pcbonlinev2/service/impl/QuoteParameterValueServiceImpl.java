package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterValueDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterValueQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteParameterValueDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteParameterValueMapper;
import com.jugu.www.pcbonlinev2.service.QuoteParameterValueService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteParameterValueService")
public class QuoteParameterValueServiceImpl extends ServiceImpl<QuoteParameterValueMapper, QuoteParameterValueDO> implements QuoteParameterValueService {

    @Autowired
    private QuoteParameterValueMapper quoteParameterValueMapper;

    @Override
    public  PageResult<List<QuoteParameterValueDTO>> queryPage(PageQuery<QuoteParameterValueQueryDTO, QuoteParameterValueDO> params) {
        ValidatorUtil.validate(params);

        QuoteParameterValueDO query = new QuoteParameterValueDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteParameterValueDO> quoteParameterValueDOPage = quoteParameterValueMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteParameterValueDTO> quoteParameterValueDTOList  = Optional.ofNullable(quoteParameterValueDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteParameterValueDO -> {
                    QuoteParameterValueDTO quoteParameterValueDTO = new QuoteParameterValueDTO();
                    BeanUtils.copyProperties(QuoteParameterValueDO, quoteParameterValueDTO);
                    return quoteParameterValueDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteParameterValueDOPage,quoteParameterValueDTOList);
    }

}