/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: Goods
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
 * 商品信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class Goods {
    private String sku; //商品SKU
    private String name; //商品名称
    private String price; //商品单价
    private String qty; //商品数量
    private String url; //商品链接
    private String attribute; //商品属性
    private String is_gift; //是否礼品:yes/no
    private String is_virtual; //是否虚拟商品:yes/no
}
