/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: CartListVO
 * Author:   zhoulei
 * Date:     2021/7/12 9:42 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 购物车返回vo
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class CartListVO {
    private Integer id; //id
    private String pno; //产品型号
    private BigDecimal subtotal; //小计
    private Integer psc; //数量
    private String gerberImgPath; //上传的gerber图片
    private Integer type; //产品类型 1pcb 2钢网 3贴片
}
