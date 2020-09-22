package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleHelpDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleHelpQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleHelpDO;
import com.jugu.www.pcbonlinev2.mapper.ArticleHelpMapper;
import com.jugu.www.pcbonlinev2.service.ArticleHelpService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("articleHelpService")
public class ArticleHelpServiceImpl extends ServiceImpl<ArticleHelpMapper, ArticleHelpDO> implements ArticleHelpService {

    @Autowired
    private ArticleHelpMapper articleHelpMapper;

    @Override
    public  PageResult<List<ArticleHelpDTO>> queryPage(PageQuery<ArticleHelpQueryDTO, ArticleHelpDO> params) {
        ValidatorUtil.validate(params);

        ArticleHelpDO query = new ArticleHelpDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<ArticleHelpDO> articleHelpDOPage = articleHelpMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<ArticleHelpDTO> articleHelpDTOList  = Optional.ofNullable(articleHelpDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ArticleHelpDO -> {
                    ArticleHelpDTO articleHelpDTO = new ArticleHelpDTO();
                    BeanUtils.copyProperties(ArticleHelpDO, articleHelpDTO);
                    return articleHelpDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(articleHelpDOPage,articleHelpDTOList);
    }

}