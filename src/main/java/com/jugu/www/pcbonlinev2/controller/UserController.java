package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.vo.UserVO;
import com.jugu.www.pcbonlinev2.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/users")
@Slf4j
@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseResult<PageResult> query(
            @NotNull Integer pageNo,
            @NotNull Integer pageSize,
            @Validated UserQueryDTO query
            ){
        log.info("query:[{}]",query);

        //构造查询条件
        PageQuery<UserQueryDTO> pageQuery = new PageQuery<>();

        pageQuery.setPageNo(pageNo);
        pageQuery.setPageSize(pageSize);
        pageQuery.setQuery(query);

        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);
        List<UserVO> userVOList = Optional
                .ofNullable(pageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(userDTO -> {
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(userDTO, userVO);

                    //对特殊字段处理
//                    if(!StringUtils.isEmpty(userDTO.getEmail())){
//
//                    }
                    return userVO;
                })
                .collect(Collectors.toList());

        //封装返回结果
        PageResult<List<UserVO>> result = new PageResult<>();

        BeanUtils.copyProperties(pageResult,result);
        result.setData(userVOList);

        return ResponseResult.success(result);
    }


}
