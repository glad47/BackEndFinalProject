package com.jugu.www.pcbonlinev2.utils;

import java.util.Map;

/**
 * 消息内容生成器
 *
 */
public interface MessageContentBuilder {

    /**
     *  模版信息绑定接口
     * @param templateName 模版名称
     * @param datas 封装的数据
     * @return html字符串
     */
    String buildMessage(String templateName, Map<String, Object> datas);
}
