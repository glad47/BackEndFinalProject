/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: ProductInfo
 * Author:   zhoulei
 * Date:     2021/5/24 9:21 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 〈〉
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class ProductInfo {
    private Integer qty; //商品数量
    private String name; //商品名称
    private BigDecimal price; //商品单价
}
