/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: Trade
 * Author:   zhoulei
 * Date:     2021/5/31 10:13 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

/**
 * 贸易信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class Trade {
    private String code; //贸易代码
    private String item; //贸易参数集合
}
