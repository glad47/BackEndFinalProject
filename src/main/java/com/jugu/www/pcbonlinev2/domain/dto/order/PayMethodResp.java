/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: PayMethodResp
 * Author:   zhoulei
 * Date:     2021/5/31 11:53 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

import java.util.List;

/**
 * 支付方式返回信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class PayMethodResp {
    private String is_redirect; //0:该笔交易不需要重定向，1:该笔交易需要重定向
    private String redirect_method; //0:GET，1:POST，2:HTML
    private String url; //重定向地址
    private List<String> redirect_param; //重定向参数(数据格式为json)
}
