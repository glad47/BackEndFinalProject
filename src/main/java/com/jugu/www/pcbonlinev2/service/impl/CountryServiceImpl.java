package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CountryDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CountryQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CountryDO;
import com.jugu.www.pcbonlinev2.mapper.CountryMapper;
import com.jugu.www.pcbonlinev2.service.CountryService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("countryService")
public class CountryServiceImpl extends ServiceImpl<CountryMapper, CountryDO> implements CountryService {

    @Autowired
    private CountryMapper countryMapper;

    @Override
    public  PageResult<List<CountryDTO>> queryPage(PageQuery<CountryQueryDTO, CountryDO> params) {
        ValidatorUtil.validate(params);

        CountryDO query = new CountryDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CountryDO> countryDOPage = countryMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CountryDTO> countryDTOList  = Optional.ofNullable(countryDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CountryDO -> {
                    CountryDTO countryDTO = new CountryDTO();
                    BeanUtils.copyProperties(CountryDO, countryDTO);
                    return countryDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(countryDOPage,countryDTOList);
    }

    @Override
    public List<CountryDTO> all() {
        // 查询配置了的国家
        List<CountryDO> countryDOList = countryMapper.queryAllConfigCountry();
//        List<CountryDO> countryDOList = countryMapper.selectList(Wrappers.emptyWrapper());

        return Optional.ofNullable(countryDOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CountryDO -> {
                    CountryDTO countryDTO = new CountryDTO();
                    BeanUtils.copyProperties(CountryDO, countryDTO);
                    return countryDTO;
                })
                .collect(Collectors.toList());
    }

}