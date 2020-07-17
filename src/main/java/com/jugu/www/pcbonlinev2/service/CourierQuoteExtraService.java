package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteExtraDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteExtraQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierQuoteExtraDO;


import java.util.List;

/**
 * 运费其它项表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface CourierQuoteExtraService extends IService<CourierQuoteExtraDO> {

    PageResult<List<CourierQuoteExtraDTO>> queryPage(PageQuery<CourierQuoteExtraQueryDTO, CourierQuoteExtraDO> params);

}

