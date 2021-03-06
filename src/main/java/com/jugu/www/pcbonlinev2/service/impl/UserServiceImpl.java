package com.jugu.www.pcbonlinev2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.mapper.UserMapper;
import com.jugu.www.pcbonlinev2.service.UserService;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,UserDO> implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public PageResult<List<UserDTO>> query(PageQuery<UserQueryDTO,UserDO> pageQuery) {

        //手动校验
        ValidatorUtil.validate(pageQuery);

//        Page<UserDO> page = new Page<>(pageQuery.getPageNo(),pageQuery.getPageSize());

        UserDO query = new UserDO();
        BeanUtils.copyProperties(pageQuery.getQuery(),query);


        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>(query);

        Page<UserDO> userDOIPage = userMapper.selectPage(pageQuery.getPage(), queryWrapper);

        //解析
//        PageResult<List<UserDTO>> pageResult = new PageResult<>();
//
//        pageResult.setPageNo((int) userDOIPage.getCurrent());
//        pageResult.setPageSize((int) userDOIPage.getSize());
//        pageResult.setPageNum(userDOIPage.getPages());
//        pageResult.setTotal(userDOIPage.getTotal());

        //数据转换
        List<UserDTO> userDTOList = Optional.ofNullable(userDOIPage.getRecords())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDO -> {
                    UserDTO userDTO = new UserDTO();
                    BeanUtils.copyProperties(userDO, userDTO);
                    return userDTO;
                })
                .collect(Collectors.toList());

//        pageResult.setData(userDTOList);

        return new PageResult<>(userDOIPage,userDTOList);
    }

    @Override
    public Integer getUserPoint(Integer userId) {
        return this.userMapper.queryUserPoint(userId);
    }

    @Override
    public void updatePoints(BigDecimal paymentTotal, Integer userId) {
        UserDO userDO = userMapper.selectById(userId);
        if (userDO.getPoints().compareTo(paymentTotal) < 0){
            throw new BusinessException(ErrorCodeEnum.USER_POINTS_INSUFFICIENT);
        }
        userDO.setPoints(userDO.getPoints().subtract(paymentTotal));

        userMapper.updateById(userDO);
    }
}
