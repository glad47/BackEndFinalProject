/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: PayAuthResponse
 * Author:   zhoulei
 * Date:     2021/5/31 11:46 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

/**
 * 信用卡支付授权返回实体
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class CardPayAuthResponse {
    private String version;
    private String merchant_id;
    private String business_id;
    private String access_type;
    private String order_number;
    private String trans_type;
    private String trans_channel;
    private String pay_method;
    private String date_complete;
    private String order_id;
    private String currency;
    private String amount;
    private String settle_currency;
    //private PayMethodResp pay_method_resp;
    private String req_reserved;
    //private Reserved reserved;
    private String status;
    private String resp_code;
    private String resp_msg;
    //private RiskResult risk_result;
    private String sign_type;
    private String sign;
}
