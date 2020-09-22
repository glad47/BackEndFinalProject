package com.jugu.www.pcbonlinev2.mapper;

import com.jugu.www.pcbonlinev2.domain.entity.CountryDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Mapper
public interface CountryMapper extends BaseMapper<CountryDO> {

    List<CountryDO> queryAllConfigCountry();
}
