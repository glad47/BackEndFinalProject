package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleCommentDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleCommentQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleCommentDO;
import com.jugu.www.pcbonlinev2.mapper.ArticleCommentMapper;
import com.jugu.www.pcbonlinev2.service.ArticleCommentService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("articleCommentService")
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleCommentDO> implements ArticleCommentService {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Override
    public  PageResult<List<ArticleCommentDTO>> queryPage(PageQuery<ArticleCommentQueryDTO, ArticleCommentDO> params) {
        ValidatorUtil.validate(params);

        ArticleCommentDO query = new ArticleCommentDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<ArticleCommentDO> articleCommentDOPage = articleCommentMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<ArticleCommentDTO> articleCommentDTOList  = Optional.ofNullable(articleCommentDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ArticleCommentDO -> {
                    ArticleCommentDTO articleCommentDTO = new ArticleCommentDTO();
                    BeanUtils.copyProperties(ArticleCommentDO, articleCommentDTO);
                    return articleCommentDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(articleCommentDOPage,articleCommentDTOList);
    }

}