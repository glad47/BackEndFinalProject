/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: OrderIds
 * Author:   zhoulei
 * Date:     2021/10/9 9:43 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

import java.util.List;

/**
 * 报价id
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class OrderIds {
    //pcb报价id
    private List<Integer> pcbIds;
    //钢网报价id
    private List<Integer> stencilIds;
    //贴片报价id
    private List<Integer> assemblyIds;
}
