/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: CardPaymentDTO
 * Author:   zhoulei
 * Date:     2021/5/24 9:11 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto;

import com.jugu.www.pcbonlinev2.domain.dto.order.PayMethodInfo;
import com.jugu.www.pcbonlinev2.domain.dto.order.ProductInfo;
import com.jugu.www.pcbonlinev2.domain.dto.order.RiskInfo;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 〈〉
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class CardPaymentDTO {
    private BigDecimal amount; //交易金额

    private String orderNumber; //订单号

    private List<ProductInfo> productInfo;

    private PayMethodInfo payMethodInfo;


    private RiskInfo riskInfo;

    private String settleCurrency; //结算币种

    //private String currency; //



}
