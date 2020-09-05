package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteLayerNumberDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteLayerNumberQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteLayerNumberDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteLayerNumberMapper;
import com.jugu.www.pcbonlinev2.service.QuoteLayerNumberService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteLayerNumberService")
public class QuoteLayerNumberServiceImpl extends ServiceImpl<QuoteLayerNumberMapper, QuoteLayerNumberDO> implements QuoteLayerNumberService {

    @Autowired
    private QuoteLayerNumberMapper quoteLayerNumberMapper;

    @Override
    public  PageResult<List<QuoteLayerNumberDTO>> queryPage(PageQuery<QuoteLayerNumberQueryDTO, QuoteLayerNumberDO> params) {
        ValidatorUtil.validate(params);

        QuoteLayerNumberDO query = new QuoteLayerNumberDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteLayerNumberDO> quoteLayerNumberDOPage = quoteLayerNumberMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteLayerNumberDTO> quoteLayerNumberDTOList  = Optional.ofNullable(quoteLayerNumberDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteLayerNumberDO -> {
                    QuoteLayerNumberDTO quoteLayerNumberDTO = new QuoteLayerNumberDTO();
                    BeanUtils.copyProperties(QuoteLayerNumberDO, quoteLayerNumberDTO);
                    return quoteLayerNumberDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteLayerNumberDOPage,quoteLayerNumberDTOList);
    }

}