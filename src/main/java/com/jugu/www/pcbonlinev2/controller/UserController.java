package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.domain.vo.UserVO;
import com.jugu.www.pcbonlinev2.service.UserService;
import io.swagger.annotations.*;
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
@Api(
        value = "用户管理",
        tags = {"用户管理Controller"},
        produces = "http, https",
        hidden = false
)
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    @ApiOperation(
            value = "查询用户信息",
            notes = "",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "pageNo",
                    value = "页码",
                    required = true,
                    paramType = "query",
                    dataType = "int"
            ),
            @ApiImplicitParam(
                    name = "pageSize",
                    value = "显示多少条",
                    required = true,
                    paramType = "query",
                    dataType = "int"
            ),
            @ApiImplicitParam(
                    name = "query",
                    value = "查询封装的对象",
                    required = false,
                    paramType = "query",
                    dataType = "object",
                    dataTypeClass = UserQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0000, message = "操作成功")
    })
    public ResponseResult<PageResult> query(
            @NotNull Integer pageNo,
            @NotNull Integer pageSize,
            @Validated UserQueryDTO query
    ){
        log.info("query:[{}]",query);

        //构造查询条件
        PageQuery<UserQueryDTO, UserDO> pageQuery = new PageQuery<>(pageNo,pageSize,query);

        //查询
        PageResult<List<UserDTO>> pageResult = userService.query(pageQuery);

        //转化VO
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

        //最终返回结果
        PageResult<List<UserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(pageResult,result);
        result.setData(userVOList);

        return ResponseResult.success(result);
    }


}
