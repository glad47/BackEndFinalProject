/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: QuoteInfo
 * Author:   zhoulei
 * Date:     2021/9/23 7:34 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import com.jugu.www.pcbonlinev2.domain.entity.AssemblyDO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteDO;
import com.jugu.www.pcbonlinev2.domain.entity.SmlStencilDO;
import lombok.Data;

/**
 * 报价信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class QuoteInfo {
    //pcb报价信息
    private QuoteDO pcbQuoteInfo;
    //钢网报价信息
    private SmlStencilDO stencilQuoteInfo;
    //贴片报价信息
    private AssemblyDO assemblyQuoteInfo;
}
