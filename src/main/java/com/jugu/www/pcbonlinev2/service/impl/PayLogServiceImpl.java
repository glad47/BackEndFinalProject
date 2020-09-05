package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.PayLogDTO;
import com.jugu.www.pcbonlinev2.domain.dto.PayLogQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.PayLogDO;
import com.jugu.www.pcbonlinev2.mapper.PayLogMapper;
import com.jugu.www.pcbonlinev2.service.PayLogService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("payLogService")
public class PayLogServiceImpl extends ServiceImpl<PayLogMapper, PayLogDO> implements PayLogService {

    @Autowired
    private PayLogMapper payLogMapper;

    @Override
    public  PageResult<List<PayLogDTO>> queryPage(PageQuery<PayLogQueryDTO, PayLogDO> params) {
        ValidatorUtil.validate(params);

        PayLogDO query = new PayLogDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<PayLogDO> payLogDOPage = payLogMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<PayLogDTO> payLogDTOList  = Optional.ofNullable(payLogDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(PayLogDO -> {
                    PayLogDTO payLogDTO = new PayLogDTO();
                    BeanUtils.copyProperties(PayLogDO, payLogDTO);
                    return payLogDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(payLogDOPage,payLogDTOList);
    }

}