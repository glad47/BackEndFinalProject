package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.WeightConfigDTO;
import com.jugu.www.pcbonlinev2.domain.dto.WeightConfigQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.WeightConfigDO;
import com.jugu.www.pcbonlinev2.mapper.WeightConfigMapper;
import com.jugu.www.pcbonlinev2.service.WeightConfigService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("weightConfigService")
public class WeightConfigServiceImpl extends ServiceImpl<WeightConfigMapper, WeightConfigDO> implements WeightConfigService {

    @Autowired
    private WeightConfigMapper weightConfigMapper;

    @Override
    public  PageResult<List<WeightConfigDTO>> queryPage(PageQuery<WeightConfigQueryDTO, WeightConfigDO> params) {
        ValidatorUtil.validate(params);

        WeightConfigDO query = new WeightConfigDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<WeightConfigDO> weightConfigDOPage = weightConfigMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<WeightConfigDTO> weightConfigDTOList  = Optional.ofNullable(weightConfigDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(WeightConfigDO -> {
                    WeightConfigDTO weightConfigDTO = new WeightConfigDTO();
                    BeanUtils.copyProperties(WeightConfigDO, weightConfigDTO);
                    return weightConfigDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(weightConfigDOPage,weightConfigDTOList);
    }

}