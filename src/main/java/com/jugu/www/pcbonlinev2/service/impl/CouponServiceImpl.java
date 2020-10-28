package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CouponDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CouponQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CountryDO;
import com.jugu.www.pcbonlinev2.domain.entity.CouponDO;
import com.jugu.www.pcbonlinev2.domain.entity.CouponRuleDO;
import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.mapper.CouponMapper;
import com.jugu.www.pcbonlinev2.service.CouponRuleService;
import com.jugu.www.pcbonlinev2.service.CouponService;
import com.jugu.www.pcbonlinev2.utils.GenSerialUtil;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("couponService")
public class CouponServiceImpl extends ServiceImpl<CouponMapper, CouponDO> implements CouponService {

    private final CouponMapper couponMapper;

    private final CouponRuleService couponRuleService;

    @Autowired
    public CouponServiceImpl(CouponMapper couponMapper, CouponRuleService couponRuleService) {
        this.couponMapper = couponMapper;
        this.couponRuleService = couponRuleService;
    }

    @Override
    public  PageResult<List<CouponDTO>> queryPage(PageQuery<CouponQueryDTO, CouponDO> params) {
        ValidatorUtil.validate(params);

        CouponDO query = new CouponDO();
        BeanUtils.copyProperties(params.getQuery(), query);

//        Page<CouponDO> couponDOPage = couponMapper.selectPage(params.getPage(), new QueryWrapper<>(query));
        List<CouponDO> couponDOList = couponMapper.queryValidCouponByUserId(query.getUserId(),1);
        //数据转换
        List<CouponDTO> couponDTOList  = Optional.ofNullable(couponDOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CouponDO -> {
                    CouponDTO couponDTO = new CouponDTO();
                    BeanUtils.copyProperties(CouponDO, couponDTO);
                    return couponDTO;
                })
                .collect(Collectors.toList());

        return null;
    }

    @Override
    public List<CouponDO> getValidCoupon(Integer userId) {
        return this.baseMapper.queryValidCouponByUserId(userId,1);
    }

    @Override
    public boolean verifyCoupon(String code, Integer userId) {
        if (!GenSerialUtil.checkCodeValid(code)) throw new BusinessException(ErrorCodeEnum.INVALID_PROMO_CODE);
        Long flag = GenSerialUtil.getFlagFromCode(code);
        CouponRuleDO ruleDO = couponRuleService.getOne(new QueryWrapper<CouponRuleDO>().eq("code_flag", flag));
        if (ruleDO == null) throw new BusinessException(ErrorCodeEnum.INVALID_PROMO_CODE);

        Integer existCount = couponMapper.selectCount(new QueryWrapper<CouponDO>().eq("coupon_code", code));
        if (existCount >= 1) throw new BusinessException(ErrorCodeEnum.COUPON_ALREADY_USED);

        CouponDO couponDO = conversionCoupon(ruleDO,userId,code);
        return this.save(couponDO);
    }

    private CouponDO conversionCoupon(CouponRuleDO ruleDO, Integer userId, String code) {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);

        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day+ruleDO.getAvailableTime());

        Date endTime = calendar.getTime();
        CouponDO couponDO = new CouponDO();
        couponDO.setCouponType(ruleDO.getId());
        couponDO.setCouponMoney(ruleDO.getMoney());
        couponDO.setUserId(userId);
        couponDO.setStartTime(nowDate);
        couponDO.setEndTime(endTime);
        couponDO.setCouponCode(code);
        couponDO.setCouponStatus(1);
        return couponDO;
    }

}