/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: RiskResult
 * Author:   zhoulei
 * Date:     2021/6/1 12:01 AM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

/**
 * 风控返回结果信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class RiskResult {
    private String riskLevel;
    private String riskMessage;
}
