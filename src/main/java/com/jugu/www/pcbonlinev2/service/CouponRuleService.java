package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CouponRuleDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CouponRuleQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CouponRuleDO;


import java.util.List;

/**
 * 优惠劵生成规则表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface CouponRuleService extends IService<CouponRuleDO> {

    PageResult<List<CouponRuleDTO>> queryPage(PageQuery<CouponRuleQueryDTO, CouponRuleDO> params);

}

