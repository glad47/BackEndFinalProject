package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MaterialQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MaterialDO;
import com.jugu.www.pcbonlinev2.mapper.MaterialMapper;
import com.jugu.www.pcbonlinev2.service.MaterialService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("materialService")
public class MaterialServiceImpl extends ServiceImpl<MaterialMapper, MaterialDO> implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public  PageResult<List<MaterialDTO>> queryPage(PageQuery<MaterialQueryDTO, MaterialDO> params) {
        ValidatorUtil.validate(params);

        MaterialDO query = new MaterialDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<MaterialDO> materialDOPage = materialMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<MaterialDTO> materialDTOList  = Optional.ofNullable(materialDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(MaterialDO -> {
                    MaterialDTO materialDTO = new MaterialDTO();
                    BeanUtils.copyProperties(MaterialDO, materialDTO);
                    return materialDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(materialDOPage,materialDTOList);
    }

}