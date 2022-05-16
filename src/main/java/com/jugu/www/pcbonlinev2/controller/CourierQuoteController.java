package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CourierQuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CourierQuoteDO;
import com.jugu.www.pcbonlinev2.domain.vo.CourierQuoteVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.CourierQuoteService;
import com.jugu.www.pcbonlinev2.validator.group.InsertValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.UpdateValidationGroup;
import io.github.yedaxia.apidocs.Ignore;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 快递报价配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/courierquote")
@Validated
@Slf4j
@Api(value = "快递报价配置表管理", tags = {"快递报价配置表controller"}, protocols = "http, https", hidden = true)
@ApiIgnore
@Ignore
public class CourierQuoteController extends BasicController<CourierQuoteDO,CourierQuoteDTO>{

    @Autowired
    private CourierQuoteService courierQuoteService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "courierQuoteDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = CourierQuoteDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody CourierQuoteDTO courierQuoteDTO) {

        if (courierQuoteService.save(conversionDO(new CourierQuoteDO(),courierQuoteDTO))){
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
                    name = "courierQuoteDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = CourierQuoteDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody CourierQuoteDTO courierQuoteDTO){

        CourierQuoteDO courierQuoteDO = conversionDO(new CourierQuoteDO(),courierQuoteDTO);
        // TODO 赋值id

        if (courierQuoteService.updateById(courierQuoteDO)){
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
        if (courierQuoteService.removeById(id)){
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }
    @ApiOperation(
            value = "查询信息",
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
                    dataTypeClass = CourierQuoteQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated CourierQuoteQueryDTO query){
        //构造查询条件
        PageQuery<CourierQuoteQueryDTO, CourierQuoteDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<CourierQuoteDTO>> listPageResult = courierQuoteService.queryPage(pageQuery);

        //转化VO
        List<CourierQuoteVO> courierQuoteVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CourierQuoteDTO -> {
                    CourierQuoteVO vo = new CourierQuoteVO();
                    BeanUtils.copyProperties(CourierQuoteDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<CourierQuoteVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(courierQuoteVOS);

        return ResponseResult.success(result);
    }


}
