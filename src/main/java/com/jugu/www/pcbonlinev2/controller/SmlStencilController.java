package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.SmlStencilDTO;
import com.jugu.www.pcbonlinev2.domain.dto.SmlStencilQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.SmlStencilDO;
import com.jugu.www.pcbonlinev2.domain.vo.SmlStencilVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.SmlStencilService;
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
 * 钢网订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@RestController
@RequestMapping("/api/smlstencil")
@Validated
@Slf4j
@Api(value = "钢网订单表管理", tags = {"钢网订单表controller"}, protocols = "http, https", hidden = false)
public class SmlStencilController extends BasicController<SmlStencilDO,SmlStencilDTO>{

    @Autowired
    private SmlStencilService smlStencilService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "smlStencilDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = SmlStencilDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody SmlStencilDTO smlStencilDTO) {
        if (smlStencilService.save(conversionDO(new SmlStencilDO(),smlStencilDTO))){
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
                    name = "smlStencilDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = SmlStencilDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody SmlStencilDTO smlStencilDTO){
        SmlStencilDO smlStencilDO = conversionDO(new SmlStencilDO(), smlStencilDTO);
        smlStencilDO.setId(id);

        if (smlStencilService.updateById(smlStencilDO)){
            return ResponseResult.success("更新成功");
        }else{
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
        if (smlStencilService.removeById(id)){
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    @ApiOperation(
            value = "查询钢网订单信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "smlStencilQueryDTO",
            value = "查询封装的对象",
            required = false,
            paramType = "body",
            dataType = "object",
            dataTypeClass = SmlStencilQueryDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/query")
    public ResponseResult<PageResult> queryPage(@Validated SmlStencilQueryDTO smlStencilQueryDTO) {
        //构造查询条件
//        PageQuery<SmlStencilQueryDTO, SmlStencilDO> pageQuery = new PageQuery<>(pageNo, pageSize, smlStencilQueryDTO);

        List<SmlStencilDO> data = smlStencilService.list(new QueryWrapper<SmlStencilDO>()
                .eq(!StringUtils.isEmpty(smlStencilQueryDTO.getStatus()), "status", smlStencilQueryDTO.getStatus())
                .in(smlStencilQueryDTO.getStatusList() != null,"status",smlStencilQueryDTO.getStatusList())
                .eq("user_id", getUserId()));

        //查询
//        PageResult<List<SmlStencilDTO>> listPageResult = smlStencilService.queryPage(pageQuery);

        //转化VO
        List<SmlStencilVO> smlStencilVOS = Optional.ofNullable(data)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(SmlStencilDTO -> {
                    SmlStencilVO vo = new SmlStencilVO();
                    BeanUtils.copyProperties(SmlStencilDTO, vo);
                    // TODO: 特殊字段处理
                    vo.setOType(2);
                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<SmlStencilVO>> result = new PageResult<>();
//        BeanUtils.copyProperties(listPageResult, result);
//        result.setData(smlStencilVOS);

        result.setData(smlStencilVOS);
        result.setTotal(Integer.valueOf(data.size()).longValue());

        return ResponseResult.success(result);
    }


}
