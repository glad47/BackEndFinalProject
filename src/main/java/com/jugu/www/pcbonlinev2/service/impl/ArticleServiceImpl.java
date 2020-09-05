package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleDO;
import com.jugu.www.pcbonlinev2.mapper.ArticleMapper;
import com.jugu.www.pcbonlinev2.service.ArticleService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("articleService")
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, ArticleDO> implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public  PageResult<List<ArticleDTO>> queryPage(PageQuery<ArticleQueryDTO, ArticleDO> params) {
        ValidatorUtil.validate(params);

        ArticleDO query = new ArticleDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<ArticleDO> articleDOPage = articleMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<ArticleDTO> articleDTOList  = Optional.ofNullable(articleDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ArticleDO -> {
                    ArticleDTO articleDTO = new ArticleDTO();
                    BeanUtils.copyProperties(ArticleDO, articleDTO);
                    return articleDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(articleDOPage,articleDTOList);
    }

}