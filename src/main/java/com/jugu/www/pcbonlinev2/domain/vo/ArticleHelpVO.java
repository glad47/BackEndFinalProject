package com.jugu.www.pcbonlinev2.domain.vo;

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
public class ArticleHelpVO implements Serializable {
	private static final long serialVersionUID = -7816700668945995757L;

		/**
	 * id
	 */
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
