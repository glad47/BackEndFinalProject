package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessUserDTO;
import com.jugu.www.pcbonlinev2.domain.dto.BusinessUserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.dto.UserQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.BusinessUserDO;
import com.jugu.www.pcbonlinev2.domain.entity.UserDO;
import com.jugu.www.pcbonlinev2.domain.vo.BusinessUserVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.BusinessUserService;
import com.jugu.www.pcbonlinev2.service.UserService;
import com.jugu.www.pcbonlinev2.validator.group.InsertValidationGroup;
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
@RequestMapping("/api/business-users")
@Validated
@Slf4j
@Api(
        value = "跟单员管理",
        tags = {"跟单员controller"},
        protocols = "http, https",
        hidden = true
)
public class BusinessUserController extends BasicController<BusinessUserDO, BusinessUserDTO>{

    @Autowired
    private BusinessUserService businessUserService;

    @Autowired
    private UserService userService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "businessUserDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = BusinessUserDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody BusinessUserDTO businessUserDTO) {
        if (businessUserService.save(conversionDO(new BusinessUserDO(), businessUserDTO))){
            return ResponseResult.success("新增成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    @ApiOperation(
            value = "修改信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "PUT"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "id",
                    value = "主键",
                    required = true,
                    paramType = "path",
                    dataType = "int"
            ),
            @ApiImplicitParam(
                    name = "businessUserDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = BusinessUserDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody BusinessUserDTO businessUserDTO) {
        BusinessUserDO businessUserDO = conversionDO(new BusinessUserDO(), businessUserDTO);
        businessUserDO.setId(id);

        if (businessUserService.updateById(businessUserDO)) {
            return ResponseResult.success("更新成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "DELETE"
    )
    @ApiImplicitParam(
            name = "id",
            value = "主键",
            required = true,
            paramType = "path",
            dataType = "int"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @DeleteMapping("/{id}")
    public ResponseResult delete(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id){
        if (businessUserService.removeById(id)){
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    @ApiOperation(
            value = "查询用户信息",
            notes = "备注",
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
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated BusinessUserQueryDTO query){
        //构造查询条件
        PageQuery<BusinessUserQueryDTO, BusinessUserDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<BusinessUserDTO>> listPageResult = businessUserService.queryPage(pageQuery);

        //转化VO
        List<BusinessUserVO> businessUserVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(businessUserDTO -> {
                    BusinessUserVO vo = new BusinessUserVO();
                    BeanUtils.copyProperties(businessUserDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<BusinessUserVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(businessUserVOS);

        return ResponseResult.success(result);
    }

    @ApiOperation(
            value = "查询当前登录用户对应跟单员信息",
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
        BusinessUserDO businessUserDO = businessUserService.getOne(new QueryWrapper<BusinessUserDO>().eq("business_id", userDO.getBusinessId()));

        BusinessUserVO vo = new BusinessUserVO();
        BeanUtils.copyProperties(businessUserDO,vo);

        return ResponseResult.success(vo);
    }


}
