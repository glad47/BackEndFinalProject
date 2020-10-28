package com.jugu.www.pcbonlinev2.aspect;

import com.jugu.www.pcbonlinev2.domain.common.Result;
import com.jugu.www.pcbonlinev2.domain.entity.CouponDO;
import com.jugu.www.pcbonlinev2.service.CouponService;
import com.jugu.www.pcbonlinev2.utils.GenSerialUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Aspect
@Component
public class CouponGrantAspect {

    private final CouponService couponService;

    @Value("${pcbonline.registered-coupon-grant}")
    private Boolean isCouponGrant;

    @Autowired
    public CouponGrantAspect(CouponService couponService) {
        this.couponService = couponService;
    }

    @Pointcut("@annotation(com.jugu.www.pcbonlinev2.aspect.CouponGrant)")
    public void processPointCut() {
    }

    @Around("processPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //执行方法
        Object result = point.proceed();

        //注册送优惠劵
        log.info("注册送优惠劵是否开启:[{}]",isCouponGrant);
        if (isCouponGrant) grantCoupon(result);
        return result;
    }

    private void grantCoupon(Object result) {
        Result r = (Result) result;
        log.info("开始送券,参数->[{}]",r.toString());
        List<CouponDO> data = new ArrayList<>();
        data.add(generateCoupon(r.getId(),50,1001));
        data.add(generateCoupon(r.getId(),20,1002));
        data.add(generateCoupon(r.getId(),20,1002));
        data.add(generateCoupon(r.getId(),5,1003));
        data.add(generateCoupon(r.getId(),5,1003));

        couponService.saveBatch(data);
    }

    private CouponDO generateCoupon(int uid, int couponMoney, int flag) {
        Date nowDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE,day+30);
        Date endTime = calendar.getTime();

        CouponDO couponDO = new CouponDO();
        couponDO.setUserId(uid);
        couponDO.setCouponMoney(couponMoney);
        couponDO.setStartTime(nowDate);
        couponDO.setEndTime(endTime);
        couponDO.setCouponStatus(1);
        couponDO.setCouponType(flag);
        couponDO.setCouponCode(GenSerialUtil.generateCode(flag));

        return couponDO;
    }

}
