/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: Bill
 * Author:   zhoulei
 * Date:     2021/5/31 10:14 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

/**
 * 账单地址
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class Bill {
    private String first_name; //账单人名
    private String last_name; //账单人姓
    private String email; //账单人联系邮箱
    private String phone; //账单人联系电话
    private String address; //账单人地址
    private String city; //账单人城市
    private String state; //账单人省份
    private String postcode; //账单人邮编
    private String country; //账单人国家英文二字码，需要大写
}
