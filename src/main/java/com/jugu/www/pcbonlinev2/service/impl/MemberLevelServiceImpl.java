package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.MemberLevelDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MemberLevelQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MemberLevelDO;
import com.jugu.www.pcbonlinev2.mapper.MemberLevelMapper;
import com.jugu.www.pcbonlinev2.service.MemberLevelService;
import com.jugu.www.pcbonlinev2.utils.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service("memberLevelService")
public class MemberLevelServiceImpl extends ServiceImpl<MemberLevelMapper, MemberLevelDO> implements MemberLevelService {

    @Autowired
    private MemberLevelMapper memberLevelMapper;

    @Override
    public  PageResult<List<MemberLevelDTO>> queryPage(PageQuery<MemberLevelQueryDTO, MemberLevelDO> params) {
        ValidatorUtil.validate(params);

        MemberLevelDO query = new MemberLevelDO();
        BeanUtils.copyProperties(params.getQuery(), query);

        Page<MemberLevelDO> memberLevelDOPage = memberLevelMapper.selectPage(params.getPage(), new QueryWrapper<>(query));

        //数据转换
        List<MemberLevelDTO> memberLevelDTOList  = Optional.ofNullable(memberLevelDOPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(MemberLevelDO -> {
                    MemberLevelDTO memberLevelDTO = new MemberLevelDTO();
                    BeanUtils.copyProperties(MemberLevelDO, memberLevelDTO);
                    return memberLevelDTO;
                })
                .collect(Collectors.toList());

        return new PageResult<>(memberLevelDOPage,memberLevelDTOList);
    }

}