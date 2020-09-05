package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.RetrievePwdDTO;
import com.jugu.www.pcbonlinev2.domain.dto.RetrievePwdQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.RetrievePwdDO;
import com.jugu.www.pcbonlinev2.mapper.RetrievePwdMapper;
import com.jugu.www.pcbonlinev2.service.RetrievePwdService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("retrievePwdService")
public class RetrievePwdServiceImpl extends ServiceImpl<RetrievePwdMapper, RetrievePwdDO> implements RetrievePwdService {

    @Autowired
    private RetrievePwdMapper retrievePwdMapper;

    @Override
    public  PageResult<List<RetrievePwdDTO>> queryPage(PageQuery<RetrievePwdQueryDTO, RetrievePwdDO> params) {
        ValidatorUtil.validate(params);

        RetrievePwdDO query = new RetrievePwdDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<RetrievePwdDO> retrievePwdDOPage = retrievePwdMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<RetrievePwdDTO> retrievePwdDTOList  = Optional.ofNullable(retrievePwdDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(RetrievePwdDO -> {
                    RetrievePwdDTO retrievePwdDTO = new RetrievePwdDTO();
                    BeanUtils.copyProperties(RetrievePwdDO, retrievePwdDTO);
                    return retrievePwdDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(retrievePwdDOPage,retrievePwdDTOList);
    }

}