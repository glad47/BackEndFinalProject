/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: Device
 * Author:   zhoulei
 * Date:     2021/5/31 10:13 PM
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.domain.dto.order;

import lombok.Builder;
import lombok.Data;

/**
 * 设备信息
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Data
public class Device {
    private String finger_print_id;// 设备指纹ID，当网站为自建站时，在视图层动态加载JS
    private String user_agent; //用户浏览器信息
    private String accept_lang; //用户浏览器语言
}
