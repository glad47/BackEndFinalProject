package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.SmlStencilDTO;
import com.jugu.www.pcbonlinev2.domain.dto.SmlStencilQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.SmlStencilDO;
import com.jugu.www.pcbonlinev2.mapper.SmlStencilMapper;
import com.jugu.www.pcbonlinev2.service.SmlStencilService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("smlStencilService")
public class SmlStencilServiceImpl extends ServiceImpl<SmlStencilMapper, SmlStencilDO> implements SmlStencilService {

    @Autowired
    private SmlStencilMapper smlStencilMapper;

    @Override
    public  PageResult<List<SmlStencilDTO>> queryPage(PageQuery<SmlStencilQueryDTO, SmlStencilDO> params) {
        ValidatorUtil.validate(params);

        SmlStencilDO query = new SmlStencilDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<SmlStencilDO> smlStencilDOPage = smlStencilMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<SmlStencilDTO> smlStencilDTOList  = Optional.ofNullable(smlStencilDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(SmlStencilDO -> {
                    SmlStencilDTO smlStencilDTO = new SmlStencilDTO();
                    BeanUtils.copyProperties(SmlStencilDO, smlStencilDTO);
                    return smlStencilDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(smlStencilDOPage,smlStencilDTOList);
    }

}