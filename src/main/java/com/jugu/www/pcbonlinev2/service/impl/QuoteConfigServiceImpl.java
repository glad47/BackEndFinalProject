package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteConfigDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteConfigQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteConfigDO;
import com.jugu.www.pcbonlinev2.mapper.QuoteConfigMapper;
import com.jugu.www.pcbonlinev2.service.QuoteConfigService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quoteConfigService")
public class QuoteConfigServiceImpl extends ServiceImpl<QuoteConfigMapper, QuoteConfigDO> implements QuoteConfigService {

    @Autowired
    private QuoteConfigMapper quoteConfigMapper;

    @Override
    public  PageResult<List<QuoteConfigDTO>> queryPage(PageQuery<QuoteConfigQueryDTO, QuoteConfigDO> params) {
        ValidatorUtil.validate(params);

        QuoteConfigDO query = new QuoteConfigDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuoteConfigDO> quoteConfigDOPage = quoteConfigMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuoteConfigDTO> quoteConfigDTOList  = Optional.ofNullable(quoteConfigDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteConfigDO -> {
                    QuoteConfigDTO quoteConfigDTO = new QuoteConfigDTO();
                    BeanUtils.copyProperties(QuoteConfigDO, quoteConfigDTO);
                    return quoteConfigDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quoteConfigDOPage,quoteConfigDTOList);
    }

}