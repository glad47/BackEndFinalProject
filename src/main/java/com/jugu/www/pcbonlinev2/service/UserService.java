package com.jugu.www.pcbonlinev2.service;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;

import java.util.List;

public interface UserService {

    PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO, UserDO> pageQuery);
}
