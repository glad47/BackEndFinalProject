package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumMetalDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuotePremiumMetalQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuotePremiumMetalDO;
import com.jugu.www.pcbonlinev2.domain.vo.QuotePremiumMetalVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.QuotePremiumMetalService;
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
 * 报价金属配置表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/quotepremiummetal")
@Validated
@Slf4j
@Api(value = "报价金属配置表管理", tags = {"报价金属配置表controller"}, protocols = "http, https", hidden = false)
public class QuotePremiumMetalController extends BasicController<QuotePremiumMetalDO,QuotePremiumMetalDTO>{

    @Autowired
    private QuotePremiumMetalService quotePremiumMetalService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "quotePremiumMetalDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = QuotePremiumMetalDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody QuotePremiumMetalDTO quotePremiumMetalDTO) {

        if (quotePremiumMetalService.save(conversionDO(new QuotePremiumMetalDO(),quotePremiumMetalDTO))){
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
                    name = "quotePremiumMetalDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = QuotePremiumMetalDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody QuotePremiumMetalDTO quotePremiumMetalDTO){

        QuotePremiumMetalDO quotePremiumMetalDO = conversionDO(new QuotePremiumMetalDO(),quotePremiumMetalDTO);
        // TODO 赋值id

        if (quotePremiumMetalService.updateById(quotePremiumMetalDO)){
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
        if (quotePremiumMetalService.removeById(id)){
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
                    dataTypeClass = QuotePremiumMetalQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated QuotePremiumMetalQueryDTO query){
        //构造查询条件
        PageQuery<QuotePremiumMetalQueryDTO, QuotePremiumMetalDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<QuotePremiumMetalDTO>> listPageResult = quotePremiumMetalService.queryPage(pageQuery);

        //转化VO
        List<QuotePremiumMetalVO> quotePremiumMetalVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuotePremiumMetalDTO -> {
                    QuotePremiumMetalVO vo = new QuotePremiumMetalVO();
                    BeanUtils.copyProperties(QuotePremiumMetalDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<QuotePremiumMetalVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(quotePremiumMetalVOS);

        return ResponseResult.success(result);
    }


}
