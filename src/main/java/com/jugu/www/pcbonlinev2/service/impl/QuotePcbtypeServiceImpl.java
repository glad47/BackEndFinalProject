package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePcbtypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePcbtypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePcbtypeDO;
import com.jugu.www.pcbonlinev2.mapper.QuotePcbtypeMapper;
import com.jugu.www.pcbonlinev2.service.QuotePcbtypeService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quotePcbtypeService")
public class QuotePcbtypeServiceImpl extends ServiceImpl<QuotePcbtypeMapper, QuotePcbtypeDO> implements QuotePcbtypeService {

    @Autowired
    private QuotePcbtypeMapper quotePcbtypeMapper;

    @Override
    public  PageResult<List<QuotePcbtypeDTO>> queryPage(PageQuery<QuotePcbtypeQueryDTO, QuotePcbtypeDO> params) {
        ValidatorUtil.validate(params);

        QuotePcbtypeDO query = new QuotePcbtypeDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuotePcbtypeDO> quotePcbtypeDOPage = quotePcbtypeMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuotePcbtypeDTO> quotePcbtypeDTOList  = Optional.ofNullable(quotePcbtypeDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuotePcbtypeDO -> {
                    QuotePcbtypeDTO quotePcbtypeDTO = new QuotePcbtypeDTO();
                    BeanUtils.copyProperties(QuotePcbtypeDO, quotePcbtypeDTO);
                    return quotePcbtypeDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quotePcbtypeDOPage,quotePcbtypeDTOList);
    }

}