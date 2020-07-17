package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentBuildDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteUrgentBuildQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteUrgentBuildDO;
import com.jugu.www.pcbonlinev2.domain.vo.QuoteUrgentBuildVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.QuoteUrgentBuildService;
import com.jugu.www.pcbonlinev2.utils.InsertValidationGroup;
import com.jugu.www.pcbonlinev2.utils.UpdateValidationGroup;
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



/**
 * 报价加工时间配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/quoteurgentbuild")
@Validated
@Slf4j
@Api(value = "报价加工时间配置表管理", tags = {"报价加工时间配置表controller"}, protocols = "http, https", hidden = false)
public class QuoteUrgentBuildController extends BasicController<QuoteUrgentBuildDO,QuoteUrgentBuildDTO>{

    @Autowired
    private QuoteUrgentBuildService quoteUrgentBuildService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "quoteUrgentBuildDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = QuoteUrgentBuildDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody QuoteUrgentBuildDTO quoteUrgentBuildDTO) {

        if (quoteUrgentBuildService.save(conversionDO(new QuoteUrgentBuildDO(),quoteUrgentBuildDTO))){
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
                    name = "quoteUrgentBuildDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = QuoteUrgentBuildDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody QuoteUrgentBuildDTO quoteUrgentBuildDTO){

        QuoteUrgentBuildDO quoteUrgentBuildDO = conversionDO(new QuoteUrgentBuildDO(),quoteUrgentBuildDTO);
        // TODO 赋值id

        if (quoteUrgentBuildService.updateById(quoteUrgentBuildDO)){
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
        if (quoteUrgentBuildService.removeById(id)){
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
                    dataTypeClass = QuoteUrgentBuildQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated QuoteUrgentBuildQueryDTO query){
        //构造查询条件
        PageQuery<QuoteUrgentBuildQueryDTO, QuoteUrgentBuildDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<QuoteUrgentBuildDTO>> listPageResult = quoteUrgentBuildService.queryPage(pageQuery);

        //转化VO
        List<QuoteUrgentBuildVO> quoteUrgentBuildVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteUrgentBuildDTO -> {
                    QuoteUrgentBuildVO vo = new QuoteUrgentBuildVO();
                    BeanUtils.copyProperties(QuoteUrgentBuildDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<QuoteUrgentBuildVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(quoteUrgentBuildVOS);

        return ResponseResult.success(result);
    }


}
