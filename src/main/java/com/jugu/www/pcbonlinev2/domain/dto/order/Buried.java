/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: Buried
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
 * 埋点数据（可以传多组）
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class Buried {
    private String code; //埋点统计模型编码
    private String item; //埋点数据
}
