package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.AssemblyDTO;
import com.jugu.www.pcbonlinev2.domain.dto.AssemblyQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.AssemblyDO;
import com.jugu.www.pcbonlinev2.mapper.AssemblyMapper;
import com.jugu.www.pcbonlinev2.service.AssemblyService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("assemblyService")
public class AssemblyServiceImpl extends ServiceImpl<AssemblyMapper, AssemblyDO> implements AssemblyService {

    @Autowired
    private AssemblyMapper assemblyMapper;

    @Override
    public  PageResult<List<AssemblyDTO>> queryPage(PageQuery<AssemblyQueryDTO, AssemblyDO> params) {
        ValidatorUtil.validate(params);

        AssemblyDO query = new AssemblyDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<AssemblyDO> assemblyDOPage = assemblyMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<AssemblyDTO> assemblyDTOList  = Optional.ofNullable(assemblyDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(AssemblyDO -> {
                    AssemblyDTO assemblyDTO = new AssemblyDTO();
                    BeanUtils.copyProperties(AssemblyDO, assemblyDTO);
                    return assemblyDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(assemblyDOPage,assemblyDTOList);
    }

}