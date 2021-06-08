/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: Ship
 * Author:   zhoulei
 * Date:     2021/5/31 10:14 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Builder;
import lombok.Data;

/**
 * 收货地址
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class Ship {
    private String first_name; //收货人名
    private String last_name; //收货人姓
    private String email; //收货人联系邮箱
    private String phone; //收货人联系电话
    private String address; //收货人地址
    private String city; //收货人城市
    private String state; //收货人省份
    private String postcode; //收货人邮编
    private String country; //收货人国家英文二字码，需要大写
    private String address_last_modify_time; //收货地址最后修改时间,yyyyMMddHHmmss， ege:20170607125959
    private String phone_last_modify_time; //收货联系电话最后修改时间,yyyyMMddHHmmss， eg:20170607125959
}
