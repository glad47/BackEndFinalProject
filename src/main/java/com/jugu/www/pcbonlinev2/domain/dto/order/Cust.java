/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: Cust
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
 * 用户参数
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class Cust {
    private String register_user_id;// 用户在商户系统的会员id
    private String ip; //用户下单ip
    private String email; //用户联系邮箱
    private String phone; //用户联系电话
    private String registration_time; //用户注册时间，yyyyMMddHHmmss，eg:20190101125959
    private String level; //用户等级标识
    private String last_shopping_time; //上一次消费时间，yyyyMMddHHmmss， eg:20190101125959
}
