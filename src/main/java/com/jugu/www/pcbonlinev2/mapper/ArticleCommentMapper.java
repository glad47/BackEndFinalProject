package com.jugu.www.pcbonlinev2.mapper;

import com.jugu.www.pcbonlinev2.domain.entity.ArticleCommentDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章评论表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Mapper
public interface ArticleCommentMapper extends BaseMapper<ArticleCommentDO> {
	
}
