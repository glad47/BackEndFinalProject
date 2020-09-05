package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CouponRuleDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CouponRuleQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CouponRuleDO;
import com.jugu.www.pcbonlinev2.mapper.CouponRuleMapper;
import com.jugu.www.pcbonlinev2.service.CouponRuleService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("couponRuleService")
public class CouponRuleServiceImpl extends ServiceImpl<CouponRuleMapper, CouponRuleDO> implements CouponRuleService {

    @Autowired
    private CouponRuleMapper couponRuleMapper;

    @Override
    public  PageResult<List<CouponRuleDTO>> queryPage(PageQuery<CouponRuleQueryDTO, CouponRuleDO> params) {
        ValidatorUtil.validate(params);

        CouponRuleDO query = new CouponRuleDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CouponRuleDO> couponRuleDOPage = couponRuleMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CouponRuleDTO> couponRuleDTOList  = Optional.ofNullable(couponRuleDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CouponRuleDO -> {
                    CouponRuleDTO couponRuleDTO = new CouponRuleDTO();
                    BeanUtils.copyProperties(CouponRuleDO, couponRuleDTO);
                    return couponRuleDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(couponRuleDOPage,couponRuleDTOList);
    }

}