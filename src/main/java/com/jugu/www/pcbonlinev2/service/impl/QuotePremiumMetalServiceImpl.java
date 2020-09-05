package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumMetalDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumMetalQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumMetalDO;
import com.jugu.www.pcbonlinev2.mapper.QuotePremiumMetalMapper;
import com.jugu.www.pcbonlinev2.service.QuotePremiumMetalService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quotePremiumMetalService")
public class QuotePremiumMetalServiceImpl extends ServiceImpl<QuotePremiumMetalMapper, QuotePremiumMetalDO> implements QuotePremiumMetalService {

    @Autowired
    private QuotePremiumMetalMapper quotePremiumMetalMapper;

    @Override
    public  PageResult<List<QuotePremiumMetalDTO>> queryPage(PageQuery<QuotePremiumMetalQueryDTO, QuotePremiumMetalDO> params) {
        ValidatorUtil.validate(params);

        QuotePremiumMetalDO query = new QuotePremiumMetalDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuotePremiumMetalDO> quotePremiumMetalDOPage = quotePremiumMetalMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuotePremiumMetalDTO> quotePremiumMetalDTOList  = Optional.ofNullable(quotePremiumMetalDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuotePremiumMetalDO -> {
                    QuotePremiumMetalDTO quotePremiumMetalDTO = new QuotePremiumMetalDTO();
                    BeanUtils.copyProperties(QuotePremiumMetalDO, quotePremiumMetalDTO);
                    return quotePremiumMetalDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quotePremiumMetalDOPage,quotePremiumMetalDTOList);
    }

}