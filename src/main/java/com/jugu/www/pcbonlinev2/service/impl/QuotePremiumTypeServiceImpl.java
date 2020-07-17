package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumTypeDO;
import com.jugu.www.pcbonlinev2.mapper.QuotePremiumTypeMapper;
import com.jugu.www.pcbonlinev2.service.QuotePremiumTypeService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("quotePremiumTypeService")
public class QuotePremiumTypeServiceImpl extends ServiceImpl<QuotePremiumTypeMapper, QuotePremiumTypeDO> implements QuotePremiumTypeService {

    @Autowired
    private QuotePremiumTypeMapper quotePremiumTypeMapper;

    @Override
    public  PageResult<List<QuotePremiumTypeDTO>> queryPage(PageQuery<QuotePremiumTypeQueryDTO, QuotePremiumTypeDO> params) {
        ValidatorUtil.validate(params);

        QuotePremiumTypeDO query = new QuotePremiumTypeDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<QuotePremiumTypeDO> quotePremiumTypeDOPage = quotePremiumTypeMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<QuotePremiumTypeDTO> quotePremiumTypeDTOList  = Optional.ofNullable(quotePremiumTypeDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuotePremiumTypeDO -> {
                    QuotePremiumTypeDTO quotePremiumTypeDTO = new QuotePremiumTypeDTO();
                    BeanUtils.copyProperties(QuotePremiumTypeDO, quotePremiumTypeDTO);
                    return quotePremiumTypeDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(quotePremiumTypeDOPage,quotePremiumTypeDTOList);
    }

}