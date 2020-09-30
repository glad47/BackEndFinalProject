package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;

import java.math.BigDecimal;
import java.util.List;

public interface UserService extends IService<UserDO> {

    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO, UserDO> pageQuery);

    Integer getUserPoint(Integer userId);

    void updatePoints(BigDecimal paymentTotal, Integer userId);
}
