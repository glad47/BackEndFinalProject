package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCompanyDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCompanyQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierCompanyDO;
import com.jugu.www.pcbonlinev2.mapper.CourierCompanyMapper;
import com.jugu.www.pcbonlinev2.service.CourierCompanyService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("courierCompanyService")
public class CourierCompanyServiceImpl extends ServiceImpl<CourierCompanyMapper, CourierCompanyDO> implements CourierCompanyService {

    @Autowired
    private CourierCompanyMapper courierCompanyMapper;

    @Override
    public  PageResult<List<CourierCompanyDTO>> queryPage(PageQuery<CourierCompanyQueryDTO, CourierCompanyDO> params) {
        ValidatorUtil.validate(params);

        CourierCompanyDO query = new CourierCompanyDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CourierCompanyDO> courierCompanyDOPage = courierCompanyMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CourierCompanyDTO> courierCompanyDTOList  = Optional.ofNullable(courierCompanyDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CourierCompanyDO -> {
                    CourierCompanyDTO courierCompanyDTO = new CourierCompanyDTO();
                    BeanUtils.copyProperties(CourierCompanyDO, courierCompanyDTO);
                    return courierCompanyDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(courierCompanyDOPage,courierCompanyDTOList);
    }

}