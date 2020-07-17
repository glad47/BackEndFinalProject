package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteParameterQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteParameterDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteParameterMapper;
import com.jugu.www.pcbonlinev2.service.QuoteParameterService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteParameterService")
public class QuoteParameterServiceImpl extends ServiceImpl<QuoteParameterMapper, QuoteParameterDO> implements QuoteParameterService {

    @Autowired
    private QuoteParameterMapper quoteParameterMapper;

    @Override
    public  PageResult<List<QuoteParameterDTO>> queryPage(PageQuery<QuoteParameterQueryDTO, QuoteParameterDO> params) {
        ValidatorUtil.validate(params);

        QuoteParameterDO query = new QuoteParameterDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteParameterDO> quoteParameterDOPage = quoteParameterMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteParameterDTO> quoteParameterDTOList  = Optional.ofNullable(quoteParameterDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteParameterDO -> {
                    QuoteParameterDTO quoteParameterDTO = new QuoteParameterDTO();
                    BeanUtils.copyProperties(QuoteParameterDO, quoteParameterDTO);
                    return quoteParameterDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteParameterDOPage,quoteParameterDTOList);
    }

}