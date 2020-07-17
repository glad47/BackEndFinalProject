package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialTypeDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialTypeQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MaterialTypeDO;
import com.jugu.www.pcbonlinev2.mapper.MaterialTypeMapper;
import com.jugu.www.pcbonlinev2.service.MaterialTypeService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("materialTypeService")
public class MaterialTypeServiceImpl extends ServiceImpl<MaterialTypeMapper, MaterialTypeDO> implements MaterialTypeService {

    @Autowired
    private MaterialTypeMapper materialTypeMapper;

    @Override
    public  PageResult<List<MaterialTypeDTO>> queryPage(PageQuery<MaterialTypeQueryDTO, MaterialTypeDO> params) {
        ValidatorUtil.validate(params);

        MaterialTypeDO query = new MaterialTypeDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<MaterialTypeDO> materialTypeDOPage = materialTypeMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<MaterialTypeDTO> materialTypeDTOList  = Optional.ofNullable(materialTypeDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(MaterialTypeDO -> {
                    MaterialTypeDTO materialTypeDTO = new MaterialTypeDTO();
                    BeanUtils.copyProperties(MaterialTypeDO, materialTypeDTO);
                    return materialTypeDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(materialTypeDOPage,materialTypeDTOList);
    }

}