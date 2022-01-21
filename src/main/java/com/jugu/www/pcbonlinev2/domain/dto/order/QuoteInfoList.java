/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: QuoteInfoList
 * Author:   zhoulei
 * Date:     2021/10/10 5:59 PM
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

import java.util.List;

/**
 * 〈〉
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class QuoteInfoList {
    private List<QuoteDO> quoteList;
    private List<SmlStencilDO> smlStencilList;
    private List<AssemblyDO> assemblyList;
}
