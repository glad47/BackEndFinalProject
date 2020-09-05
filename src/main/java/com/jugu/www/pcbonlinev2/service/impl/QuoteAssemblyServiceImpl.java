package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAssemblyDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteAssemblyQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteAssemblyDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteAssemblyMapper;
import com.jugu.www.pcbonlinev2.service.QuoteAssemblyService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteAssemblyService")
public class QuoteAssemblyServiceImpl extends ServiceImpl<QuoteAssemblyMapper, QuoteAssemblyDO> implements QuoteAssemblyService {

    @Autowired
    private QuoteAssemblyMapper quoteAssemblyMapper;

    @Override
    public  PageResult<List<QuoteAssemblyDTO>> queryPage(PageQuery<QuoteAssemblyQueryDTO, QuoteAssemblyDO> params) {
        ValidatorUtil.validate(params);

        QuoteAssemblyDO query = new QuoteAssemblyDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteAssemblyDO> quoteAssemblyDOPage = quoteAssemblyMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteAssemblyDTO> quoteAssemblyDTOList  = Optional.ofNullable(quoteAssemblyDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteAssemblyDO -> {
                    QuoteAssemblyDTO quoteAssemblyDTO = new QuoteAssemblyDTO();
                    BeanUtils.copyProperties(QuoteAssemblyDO, quoteAssemblyDTO);
                    return quoteAssemblyDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteAssemblyDOPage,quoteAssemblyDTOList);
    }

}