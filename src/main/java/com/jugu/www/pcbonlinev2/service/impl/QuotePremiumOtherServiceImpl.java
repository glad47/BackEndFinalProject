package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumOtherDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumOtherQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumOtherDO;
import com.jugu.www.pcbonlinev2.mapper.QuotePremiumOtherMapper;
import com.jugu.www.pcbonlinev2.service.QuotePremiumOtherService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quotePremiumOtherService")
public class QuotePremiumOtherServiceImpl extends ServiceImpl<QuotePremiumOtherMapper, QuotePremiumOtherDO> implements QuotePremiumOtherService {

    @Autowired
    private QuotePremiumOtherMapper quotePremiumOtherMapper;

    @Override
    public  PageResult<List<QuotePremiumOtherDTO>> queryPage(PageQuery<QuotePremiumOtherQueryDTO, QuotePremiumOtherDO> params) {
        ValidatorUtil.validate(params);

        QuotePremiumOtherDO query = new QuotePremiumOtherDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuotePremiumOtherDO> quotePremiumOtherDOPage = quotePremiumOtherMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuotePremiumOtherDTO> quotePremiumOtherDTOList  = Optional.ofNullable(quotePremiumOtherDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuotePremiumOtherDO -> {
                    QuotePremiumOtherDTO quotePremiumOtherDTO = new QuotePremiumOtherDTO();
                    BeanUtils.copyProperties(QuotePremiumOtherDO, quotePremiumOtherDTO);
                    return quotePremiumOtherDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quotePremiumOtherDOPage,quotePremiumOtherDTOList);
    }

}