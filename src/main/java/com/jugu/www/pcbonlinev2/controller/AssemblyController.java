package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.AssemblyDTO;
import com.jugu.www.pcbonlinev2.domain.dto.AssemblyQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.AssemblyDO;
import com.jugu.www.pcbonlinev2.domain.vo.AssemblyVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.AssemblyService;
import com.jugu.www.pcbonlinev2.validator.group.InsertValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.UpdateValidationGroup;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 切片订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@RestController
@RequestMapping("/api/assembly")
@Validated
@Slf4j
@Api(value = "切片订单表管理", tags = {"切片订单表controller"}, protocols = "http, https", hidden = true)
public class AssemblyController extends BasicController<AssemblyDO, AssemblyDTO> {

    @Autowired
    private AssemblyService assemblyService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "assemblyDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = AssemblyDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody AssemblyDTO assemblyDTO) {
        if (assemblyService.save(conversionDO(new AssemblyDO(), assemblyDTO))) {
            return ResponseResult.success("新增成功");
        } else {
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
                    name = "assemblyDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = AssemblyDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody AssemblyDTO assemblyDTO) {
        AssemblyDO assemblyDO = conversionDO(new AssemblyDO(), assemblyDTO);
        assemblyDO.setId(id);

        if (assemblyService.updateById(assemblyDO)) {
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
    public ResponseResult delete(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id) {
        if (assemblyService.removeById(id)) {
            return ResponseResult.success("删除成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    @ApiOperation(
            value = "查询贴片订单信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "assemblyQueryDTO",
            value = "查询封装的对象",
            required = false,
            paramType = "body",
            dataType = "object",
            dataTypeClass = AssemblyQueryDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/query")
    public ResponseResult<PageResult> queryPage(@Validated @RequestBody AssemblyQueryDTO assemblyQueryDTO) {
        //构造查询条件
//        PageQuery<AssemblyQueryDTO, AssemblyDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
//        PageResult<List<AssemblyDTO>> listPageResult = assemblyService.queryPage(pageQuery);

        List<AssemblyDO> data = assemblyService.list(new QueryWrapper<AssemblyDO>()
                .eq(!StringUtils.isEmpty(assemblyQueryDTO.getStatus()), "status", assemblyQueryDTO.getStatus())
                .in(assemblyQueryDTO.getStatusList()!=null,"status",assemblyQueryDTO.getStatusList())
                .eq("user_id", getUserId()));

        //转化VO
        List<AssemblyVO> assemblyVOS = Optional.ofNullable(data)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(AssemblyDTO -> {
                    AssemblyVO vo = new AssemblyVO();
                    BeanUtils.copyProperties(AssemblyDTO, vo);
                    // TODO: 特殊字段处理
                    vo.setOType(3);
                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<AssemblyVO>> result = new PageResult<>();
//        BeanUtils.copyProperties(listPageResult, result);
//        result.setData(assemblyVOS);
        result.setData(assemblyVOS);
        result.setTotal(Integer.valueOf(assemblyVOS.size()).longValue());

        return ResponseResult.success(result);
    }


}
