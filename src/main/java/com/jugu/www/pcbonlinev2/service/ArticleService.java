package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleDO;


import java.util.List;

/**
 * 文章表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
public interface ArticleService extends IService<ArticleDO> {

    PageResult<List<ArticleDTO>> queryPage(PageQuery<ArticleQueryDTO, ArticleDO> params);

}

