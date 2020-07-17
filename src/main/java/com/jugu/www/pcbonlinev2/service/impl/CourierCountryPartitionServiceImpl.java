package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCountryPartitionDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierCountryPartitionQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierCountryPartitionDO;
import com.jugu.www.pcbonlinev2.mapper.CourierCountryPartitionMapper;
import com.jugu.www.pcbonlinev2.service.CourierCountryPartitionService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("courierCountryPartitionService")
public class CourierCountryPartitionServiceImpl extends ServiceImpl<CourierCountryPartitionMapper, CourierCountryPartitionDO> implements CourierCountryPartitionService {

    @Autowired
    private CourierCountryPartitionMapper courierCountryPartitionMapper;

    @Override
    public  PageResult<List<CourierCountryPartitionDTO>> queryPage(PageQuery<CourierCountryPartitionQueryDTO, CourierCountryPartitionDO> params) {
        ValidatorUtil.validate(params);

        CourierCountryPartitionDO query = new CourierCountryPartitionDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CourierCountryPartitionDO> courierCountryPartitionDOPage = courierCountryPartitionMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CourierCountryPartitionDTO> courierCountryPartitionDTOList  = Optional.ofNullable(courierCountryPartitionDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CourierCountryPartitionDO -> {
                    CourierCountryPartitionDTO courierCountryPartitionDTO = new CourierCountryPartitionDTO();
                    BeanUtils.copyProperties(CourierCountryPartitionDO, courierCountryPartitionDTO);
                    return courierCountryPartitionDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(courierCountryPartitionDOPage,courierCountryPartitionDTOList);
    }

}