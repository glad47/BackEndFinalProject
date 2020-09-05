package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteStencilDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteStencilQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteStencilDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteStencilMapper;
import com.jugu.www.pcbonlinev2.service.QuoteStencilService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteStencilService")
public class QuoteStencilServiceImpl extends ServiceImpl<QuoteStencilMapper, QuoteStencilDO> implements QuoteStencilService {

    @Autowired
    private QuoteStencilMapper quoteStencilMapper;

    @Override
    public  PageResult<List<QuoteStencilDTO>> queryPage(PageQuery<QuoteStencilQueryDTO, QuoteStencilDO> params) {
        ValidatorUtil.validate(params);

        QuoteStencilDO query = new QuoteStencilDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteStencilDO> quoteStencilDOPage = quoteStencilMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteStencilDTO> quoteStencilDTOList  = Optional.ofNullable(quoteStencilDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteStencilDO -> {
                    QuoteStencilDTO quoteStencilDTO = new QuoteStencilDTO();
                    BeanUtils.copyProperties(QuoteStencilDO, quoteStencilDTO);
                    return quoteStencilDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteStencilDOPage,quoteStencilDTOList);
    }

}