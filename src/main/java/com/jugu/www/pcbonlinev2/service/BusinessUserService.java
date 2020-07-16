package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessUserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessUserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessUserDO;

import java.util.List;

public interface BusinessUserService extends IService<BusinessUserDO> {

    PageResult<List<BusinessUserDTO>> queryPage(PageQuery<BusinessUserQueryDTO, BusinessUserDO> pageQuery);
}
