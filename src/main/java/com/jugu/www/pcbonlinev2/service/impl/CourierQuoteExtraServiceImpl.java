package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteExtraDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteExtraQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierQuoteExtraDO;
import com.jugu.www.pcbonlinev2.mapper.CourierQuoteExtraMapper;
import com.jugu.www.pcbonlinev2.service.CourierQuoteExtraService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("courierQuoteExtraService")
public class CourierQuoteExtraServiceImpl extends ServiceImpl<CourierQuoteExtraMapper, CourierQuoteExtraDO> implements CourierQuoteExtraService {

    @Autowired
    private CourierQuoteExtraMapper courierQuoteExtraMapper;

    @Override
    public  PageResult<List<CourierQuoteExtraDTO>> queryPage(PageQuery<CourierQuoteExtraQueryDTO, CourierQuoteExtraDO> params) {
        ValidatorUtil.validate(params);

        CourierQuoteExtraDO query = new CourierQuoteExtraDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CourierQuoteExtraDO> courierQuoteExtraDOPage = courierQuoteExtraMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CourierQuoteExtraDTO> courierQuoteExtraDTOList  = Optional.ofNullable(courierQuoteExtraDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CourierQuoteExtraDO -> {
                    CourierQuoteExtraDTO courierQuoteExtraDTO = new CourierQuoteExtraDTO();
                    BeanUtils.copyProperties(CourierQuoteExtraDO, courierQuoteExtraDTO);
                    return courierQuoteExtraDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(courierQuoteExtraDOPage,courierQuoteExtraDTOList);
    }

}