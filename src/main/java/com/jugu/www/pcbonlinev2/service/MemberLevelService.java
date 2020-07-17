package com.jugu.www.pcbonlinev2.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MemberLevelDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MemberLevelQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MemberLevelDO;


import java.util.List;

/**
 * 会员等级表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
public interface MemberLevelService extends IService<MemberLevelDO> {

    PageResult<List<MemberLevelDTO>> queryPage(PageQuery<MemberLevelQueryDTO, MemberLevelDO> params);

}

