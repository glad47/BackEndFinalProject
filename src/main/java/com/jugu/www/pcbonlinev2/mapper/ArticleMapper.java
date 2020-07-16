package com.jugu.www.pcbonlinev2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文章表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@Mapper
public interface ArticleMapper extends BaseMapper<ArticleDO> {
	
}
