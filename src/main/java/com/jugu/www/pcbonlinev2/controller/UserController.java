package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.UserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.domain.vo.UserVO;
import com.jugu.www.pcbonlinev2.exception.BusinessException;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.UserService;
import com.jugu.www.pcbonlinev2.utils.SHA256Util;
import com.jugu.www.pcbonlinev2.validator.group.UpdateValidationGroup;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
public class UserController extends BasicController<UserDO,UserDTO>{

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

    @ApiOperation(
            value = "修改信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "PUT"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "userDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = UserDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping()
    public ResponseResult update(@Validated(UpdateValidationGroup.class) @RequestBody UserDTO userDTO){
        log.info("userId:[{}]",getUserId());
        UserDO userDO = conversionDO(new UserDO(),userDTO);
        userDO.setId(getUserId());

        if (userService.updateById(userDO)){
            return ResponseResult.success("更新成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }


    @ApiOperation(
            value = "查询当前登录用户信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiResponses({
            @ApiResponse(code = 0000, message = "操作成功")
    })
    @GetMapping("/info")
    public ResponseResult info() {
        UserDO userDO = userService.getById(getUserId());
        UserVO userVO = Optional.ofNullable(userDO)
                .map(u -> {
                    UserVO vo = new UserVO();
                    BeanUtils.copyProperties(u, vo);
                    return vo;
                }).orElseThrow(() -> new BusinessException(ErrorCodeEnum.USER_LOGIN_INFO_NULL));
        return ResponseResult.success(userVO);
    }


    @ApiOperation(
            value = "校验当前密码",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "currPwd",
            value = "当前密码",
            required = true,
            paramType = "query",
            dataType = "string"
    )
    @ApiResponses({
            @ApiResponse(code = 0000, message = "操作成功")
    })
    @PostMapping("/verifyCurrPwd")
    public ResponseResult verifyCurrPwd(@NotNull(message = "当前密码不能为null！") String currPwd) {
        UserDO userDO = userService.getById(getUserId());
        return ResponseResult.success(userDO.getPassword().equals(SHA256Util.getSHA256StrJava(currPwd.trim() + "password")));
    }

}
