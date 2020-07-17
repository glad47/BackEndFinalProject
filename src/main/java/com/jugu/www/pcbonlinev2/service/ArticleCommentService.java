package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleCommentDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleCommentQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleCommentDO;


import java.util.List;

/**
 * 文章评论表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface ArticleCommentService extends IService<ArticleCommentDO> {

    PageResult<List<ArticleCommentDTO>> queryPage(PageQuery<ArticleCommentQueryDTO, ArticleCommentDO> params);

}

