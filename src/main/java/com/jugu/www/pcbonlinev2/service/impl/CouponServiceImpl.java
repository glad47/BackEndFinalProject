package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CouponDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CouponQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CouponDO;
import com.jugu.www.pcbonlinev2.mapper.CouponMapper;
import com.jugu.www.pcbonlinev2.service.CouponService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponMapper, CouponDO> implements CouponService {

    @Autowired
    private CouponMapper couponMapper;

    @Override
    public  PageResult<List<CouponDTO>> queryPage(PageQuery<CouponQueryDTO, CouponDO> params) {
        ValidatorUtil.validate(params);

        CouponDO query = new CouponDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<CouponDO> couponDOPage = couponMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<CouponDTO> couponDTOList  = Optional.ofNullable(couponDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CouponDO -> {
                    CouponDTO couponDTO = new CouponDTO();
                    BeanUtils.copyProperties(CouponDO, couponDTO);
                    return couponDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(couponDOPage,couponDTOList);
    }

}