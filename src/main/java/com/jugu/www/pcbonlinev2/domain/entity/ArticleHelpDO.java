package com.jugu.www.pcbonlinev2.domain.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 帮助主题表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-17 10:29:18
 */
@Data
@TableName("article_help")
public class ArticleHelpDO implements Serializable {
    // TODO serialVersionUid
    /**
     * id
     */
    @TableId
    private Integer id;
    /**
     * 主题
     */
    private String title;
    /**
     * 一级分类
     */
    private String classifyOne;
    /**
     * 二级分类
     */
    private String classifyTwo;
    /**
     * 内容
     */
    private String content;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;

}
