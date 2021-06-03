/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: CardInfo
 * Author:   zhoulei
 * Date:     2021/5/31 11:59 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Data;

/**
 * 卡信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class CardInfo {
    private String card_bill;
    private String card_country;
    private String card_mask;
    private String card_level;
}
