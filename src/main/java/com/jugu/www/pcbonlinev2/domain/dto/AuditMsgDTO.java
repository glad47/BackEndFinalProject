/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: AuditMsgDTO
 * Author:   zhoulei
 * Date:     2021/9/4 4:04 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto;

import com.jugu.www.pcbonlinev2.domain.dto.order.AuditOrderItem;
import lombok.Data;

import java.util.List;

/**
 * 审核消息传参
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class AuditMsgDTO {
    private List<AuditOrderItem> orderDetailsList; //订单详细
    private String remark; //备注
}
