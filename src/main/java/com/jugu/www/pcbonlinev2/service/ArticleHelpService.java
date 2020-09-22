package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleHelpDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleHelpQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleHelpDO;


import java.util.List;

/**
 * 帮助主题表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-17 10:29:18
 */
public interface ArticleHelpService extends IService<ArticleHelpDO> {

    PageResult<List<ArticleHelpDTO>> queryPage(PageQuery<ArticleHelpQueryDTO, ArticleHelpDO> params);

}

