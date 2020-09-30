package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CouponDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CouponQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CouponDO;


import java.util.List;

/**
 * 优惠券表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface CouponService extends IService<CouponDO> {

    PageResult<List<CouponDTO>> queryPage(PageQuery<CouponQueryDTO, CouponDO> params);

    List<CouponDO> getValidCoupon(Integer userId);

    boolean verifyCoupon(String code, Integer userId);
}

