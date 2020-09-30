package com.jugu.www.pcbonlinev2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    /**
     * 查询用户点数
     * @param userId
     * @return
     */
    Integer queryUserPoint(@Param("userId") Integer userId);
}
