package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ZanDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ZanQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ZanDO;
import com.jugu.www.pcbonlinev2.mapper.ZanMapper;
import com.jugu.www.pcbonlinev2.service.ZanService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("zanService")
public class ZanServiceImpl extends ServiceImpl<ZanMapper, ZanDO> implements ZanService {

    @Autowired
    private ZanMapper zanMapper;

    @Override
    public  PageResult<List<ZanDTO>> queryPage(PageQuery<ZanQueryDTO, ZanDO> params) {
        ValidatorUtil.validate(params);

        ZanDO query = new ZanDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<ZanDO> zanDOPage = zanMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<ZanDTO> zanDTOList  = Optional.ofNullable(zanDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ZanDO -> {
                    ZanDTO zanDTO = new ZanDTO();
                    BeanUtils.copyProperties(ZanDO, zanDTO);
                    return zanDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(zanDOPage,zanDTOList);
    }

}