/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: AuditOrderItem
 * Author:   zhoulei
 * Date:     2021/9/4 4:06 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

/**
 * 〈〉
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class AuditOrderItem {
    private Integer id; //产品id

    private String productNo; //产品编号

    private Integer type; //产品类型 1pcb 2钢网 3贴片
}
