package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessTargetDTO;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessTargetQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessTargetDO;
import com.jugu.www.pcbonlinev2.mapper.BusinessTargetMapper;
import com.jugu.www.pcbonlinev2.service.BusinessTargetService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("businessTargetService")
public class BusinessTargetServiceImpl extends ServiceImpl<BusinessTargetMapper, BusinessTargetDO> implements BusinessTargetService {

    @Autowired
    private BusinessTargetMapper businessTargetMapper;

    @Override
    public  PageResult<List<BusinessTargetDTO>> queryPage(PageQuery<BusinessTargetQueryDTO, BusinessTargetDO> params) {
        ValidatorUtil.validate(params);

        BusinessTargetDO query = new BusinessTargetDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<BusinessTargetDO> businessTargetDOPage = businessTargetMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<BusinessTargetDTO> businessTargetDTOList  = Optional.ofNullable(businessTargetDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(BusinessTargetDO -> {
                    BusinessTargetDTO businessTargetDTO = new BusinessTargetDTO();
                    BeanUtils.copyProperties(BusinessTargetDO, businessTargetDTO);
                    return businessTargetDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(businessTargetDOPage,businessTargetDTOList);
    }

}