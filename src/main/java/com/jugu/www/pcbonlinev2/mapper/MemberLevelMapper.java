package com.jugu.www.pcbonlinev2.mapper;

import com.jugu.www.pcbonlinev2.domain.entity.MemberLevelDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 会员等级表
 * 
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@Mapper
public interface MemberLevelMapper extends BaseMapper<MemberLevelDO> {

    MemberLevelDO queryMemberLevel(@Param("points") Integer points);
}
