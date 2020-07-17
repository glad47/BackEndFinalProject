package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CityDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CityQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CityDO;
import com.jugu.www.pcbonlinev2.mapper.CityMapper;
import com.jugu.www.pcbonlinev2.service.CityService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("cityService")
public class CityServiceImpl extends ServiceImpl<CityMapper, CityDO> implements CityService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public  PageResult<List<CityDTO>> queryPage(PageQuery<CityQueryDTO, CityDO> params) {
        ValidatorUtil.validate(params);

        CityDO query = new CityDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CityDO> cityDOPage = cityMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CityDTO> cityDTOList  = Optional.ofNullable(cityDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CityDO -> {
                    CityDTO cityDTO = new CityDTO();
                    BeanUtils.copyProperties(CityDO, cityDTO);
                    return cityDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(cityDOPage,cityDTOList);
    }

}