package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessUserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessUserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessUserDO;
import com.jugu.www.pcbonlinev2.mapper.BusinessUserMapper;
import com.jugu.www.pcbonlinev2.service.BusinessUserService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("businessUserService")
public class BusinessUserServiceImpl extends ServiceImpl<BusinessUserMapper, BusinessUserDO> implements BusinessUserService {

    @Autowired
    private BusinessUserMapper businessUserMapper;

    @Override
    public PageResult<List<BusinessUserDTO>> queryPage(PageQuery<BusinessUserQueryDTO,BusinessUserDO> pageQuery) {
        ValidatorUtil.validate(pageQuery);

        BusinessUserDO query = new BusinessUserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(), query);

        Page<BusinessUserDO> businessUserDOPage = businessUserMapper.selectPage(pageQuery.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<BusinessUserDTO> businessUserDTOList  = Optional.ofNullable(businessUserDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(BusinessUserDO -> {
                    BusinessUserDTO businessUserDTO = new BusinessUserDTO();
                    BeanUtils.copyProperties(BusinessUserDO, businessUserDTO);
                    return businessUserDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(businessUserDOPage,businessUserDTOList);
    }
}
