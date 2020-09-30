package com.jugu.www.pcbonlinev2.mapper;

import com.jugu.www.pcbonlinev2.domain.entity.CouponDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 优惠券表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Mapper
public interface CouponMapper extends BaseMapper<CouponDO> {

    List<CouponDO> queryValidCouponByUserId(@Param("userId") Integer userId, @Param("state") Integer state);
}
