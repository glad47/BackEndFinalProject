package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumNoMetalDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumNoMetalQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumNoMetalDO;
import com.jugu.www.pcbonlinev2.mapper.QuotePremiumNoMetalMapper;
import com.jugu.www.pcbonlinev2.service.QuotePremiumNoMetalService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quotePremiumNoMetalService")
public class QuotePremiumNoMetalServiceImpl extends ServiceImpl<QuotePremiumNoMetalMapper, QuotePremiumNoMetalDO> implements QuotePremiumNoMetalService {

    @Autowired
    private QuotePremiumNoMetalMapper quotePremiumNoMetalMapper;

    @Override
    public  PageResult<List<QuotePremiumNoMetalDTO>> queryPage(PageQuery<QuotePremiumNoMetalQueryDTO, QuotePremiumNoMetalDO> params) {
        ValidatorUtil.validate(params);

        QuotePremiumNoMetalDO query = new QuotePremiumNoMetalDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuotePremiumNoMetalDO> quotePremiumNoMetalDOPage = quotePremiumNoMetalMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuotePremiumNoMetalDTO> quotePremiumNoMetalDTOList  = Optional.ofNullable(quotePremiumNoMetalDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuotePremiumNoMetalDO -> {
                    QuotePremiumNoMetalDTO quotePremiumNoMetalDTO = new QuotePremiumNoMetalDTO();
                    BeanUtils.copyProperties(QuotePremiumNoMetalDO, quotePremiumNoMetalDTO);
                    return quotePremiumNoMetalDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quotePremiumNoMetalDOPage,quotePremiumNoMetalDTOList);
    }

}