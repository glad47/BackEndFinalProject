package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ProductionLogDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ProductionLogQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ProductionLogDO;
import com.jugu.www.pcbonlinev2.mapper.ProductionLogMapper;
import com.jugu.www.pcbonlinev2.service.ProductionLogService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("productionLogService")
public class ProductionLogServiceImpl extends ServiceImpl<ProductionLogMapper, ProductionLogDO> implements ProductionLogService {

    @Autowired
    private ProductionLogMapper productionLogMapper;

    @Override
    public  PageResult<List<ProductionLogDTO>> queryPage(PageQuery<ProductionLogQueryDTO, ProductionLogDO> params) {
        ValidatorUtil.validate(params);

        ProductionLogDO query = new ProductionLogDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<ProductionLogDO> productionLogDOPage = productionLogMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<ProductionLogDTO> productionLogDTOList  = Optional.ofNullable(productionLogDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ProductionLogDO -> {
                    ProductionLogDTO productionLogDTO = new ProductionLogDTO();
                    BeanUtils.copyProperties(ProductionLogDO, productionLogDTO);
                    return productionLogDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(productionLogDOPage,productionLogDTOList);
    }

}