/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: PayMethodInfo
 * Author:   zhoulei
 * Date:     2021/5/24 9:24 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

/**
 * 交易信用卡信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class PayMethodInfo {
    private String card_no; //持卡人卡号
    private String expiration_month; //有效月
    private String expiration_year; //有效年
    private String cvv; //安全码
    private String first_name; //名字
    private String last_name; //姓
    private String billing_desc; //账单描述
}
