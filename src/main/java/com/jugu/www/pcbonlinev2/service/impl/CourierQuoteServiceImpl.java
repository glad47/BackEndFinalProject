package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierQuoteDO;
import com.jugu.www.pcbonlinev2.mapper.CourierQuoteMapper;
import com.jugu.www.pcbonlinev2.service.CourierQuoteService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("courierQuoteService")
public class CourierQuoteServiceImpl extends ServiceImpl<CourierQuoteMapper, CourierQuoteDO> implements CourierQuoteService {

    @Autowired
    private CourierQuoteMapper courierQuoteMapper;

    @Override
    public  PageResult<List<CourierQuoteDTO>> queryPage(PageQuery<CourierQuoteQueryDTO, CourierQuoteDO> params) {
        ValidatorUtil.validate(params);

        CourierQuoteDO query = new CourierQuoteDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CourierQuoteDO> courierQuoteDOPage = courierQuoteMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CourierQuoteDTO> courierQuoteDTOList  = Optional.ofNullable(courierQuoteDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CourierQuoteDO -> {
                    CourierQuoteDTO courierQuoteDTO = new CourierQuoteDTO();
                    BeanUtils.copyProperties(CourierQuoteDO, courierQuoteDTO);
                    return courierQuoteDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(courierQuoteDOPage,courierQuoteDTOList);
    }

}