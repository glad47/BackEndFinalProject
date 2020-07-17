package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentBuildDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentBuildQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteUrgentBuildDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteUrgentBuildMapper;
import com.jugu.www.pcbonlinev2.service.QuoteUrgentBuildService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteUrgentBuildService")
public class QuoteUrgentBuildServiceImpl extends ServiceImpl<QuoteUrgentBuildMapper, QuoteUrgentBuildDO> implements QuoteUrgentBuildService {

    @Autowired
    private QuoteUrgentBuildMapper quoteUrgentBuildMapper;

    @Override
    public  PageResult<List<QuoteUrgentBuildDTO>> queryPage(PageQuery<QuoteUrgentBuildQueryDTO, QuoteUrgentBuildDO> params) {
        ValidatorUtil.validate(params);

        QuoteUrgentBuildDO query = new QuoteUrgentBuildDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteUrgentBuildDO> quoteUrgentBuildDOPage = quoteUrgentBuildMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteUrgentBuildDTO> quoteUrgentBuildDTOList  = Optional.ofNullable(quoteUrgentBuildDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteUrgentBuildDO -> {
                    QuoteUrgentBuildDTO quoteUrgentBuildDTO = new QuoteUrgentBuildDTO();
                    BeanUtils.copyProperties(QuoteUrgentBuildDO, quoteUrgentBuildDTO);
                    return quoteUrgentBuildDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteUrgentBuildDOPage,quoteUrgentBuildDTOList);
    }

}