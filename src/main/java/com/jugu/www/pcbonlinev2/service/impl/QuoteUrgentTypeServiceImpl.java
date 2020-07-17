package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteUrgentTypeDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteUrgentTypeMapper;
import com.jugu.www.pcbonlinev2.service.QuoteUrgentTypeService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteUrgentTypeService")
public class QuoteUrgentTypeServiceImpl extends ServiceImpl<QuoteUrgentTypeMapper, QuoteUrgentTypeDO> implements QuoteUrgentTypeService {

    @Autowired
    private QuoteUrgentTypeMapper quoteUrgentTypeMapper;

    @Override
    public  PageResult<List<QuoteUrgentTypeDTO>> queryPage(PageQuery<QuoteUrgentTypeQueryDTO, QuoteUrgentTypeDO> params) {
        ValidatorUtil.validate(params);

        QuoteUrgentTypeDO query = new QuoteUrgentTypeDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteUrgentTypeDO> quoteUrgentTypeDOPage = quoteUrgentTypeMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteUrgentTypeDTO> quoteUrgentTypeDTOList  = Optional.ofNullable(quoteUrgentTypeDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteUrgentTypeDO -> {
                    QuoteUrgentTypeDTO quoteUrgentTypeDTO = new QuoteUrgentTypeDTO();
                    BeanUtils.copyProperties(QuoteUrgentTypeDO, quoteUrgentTypeDTO);
                    return quoteUrgentTypeDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteUrgentTypeDOPage,quoteUrgentTypeDTOList);
    }

}