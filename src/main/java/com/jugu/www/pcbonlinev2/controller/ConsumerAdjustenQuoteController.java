package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.ConsumerAdjustenQuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ConsumerAdjustenQuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ConsumerAdjustenQuoteDO;
import com.jugu.www.pcbonlinev2.domain.vo.ConsumerAdjustenQuoteVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.ConsumerAdjustenQuoteService;
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
 * 客户调整价格报价表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/consumeradjustenquote")
@Validated
@Slf4j
@Api(value = "客户调整价格报价表管理", tags = {"客户调整价格报价表controller"}, protocols = "http, https", hidden = false)
public class ConsumerAdjustenQuoteController extends BasicController<ConsumerAdjustenQuoteDO,ConsumerAdjustenQuoteDTO>{

    @Autowired
    private ConsumerAdjustenQuoteService consumerAdjustenQuoteService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "consumerAdjustenQuoteDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = ConsumerAdjustenQuoteDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody ConsumerAdjustenQuoteDTO consumerAdjustenQuoteDTO) {

        if (consumerAdjustenQuoteService.save(conversionDO(new ConsumerAdjustenQuoteDO(),consumerAdjustenQuoteDTO))){
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
                    name = "consumerAdjustenQuoteDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = ConsumerAdjustenQuoteDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody ConsumerAdjustenQuoteDTO consumerAdjustenQuoteDTO){

        ConsumerAdjustenQuoteDO consumerAdjustenQuoteDO = conversionDO(new ConsumerAdjustenQuoteDO(),consumerAdjustenQuoteDTO);
        // TODO 赋值id

        if (consumerAdjustenQuoteService.updateById(consumerAdjustenQuoteDO)){
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
        if (consumerAdjustenQuoteService.removeById(id)){
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
                    dataTypeClass = ConsumerAdjustenQuoteQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated ConsumerAdjustenQuoteQueryDTO query){
        //构造查询条件
        PageQuery<ConsumerAdjustenQuoteQueryDTO, ConsumerAdjustenQuoteDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<ConsumerAdjustenQuoteDTO>> listPageResult = consumerAdjustenQuoteService.queryPage(pageQuery);

        //转化VO
        List<ConsumerAdjustenQuoteVO> consumerAdjustenQuoteVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ConsumerAdjustenQuoteDTO -> {
                    ConsumerAdjustenQuoteVO vo = new ConsumerAdjustenQuoteVO();
                    BeanUtils.copyProperties(ConsumerAdjustenQuoteDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<ConsumerAdjustenQuoteVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(consumerAdjustenQuoteVOS);

        return ResponseResult.success(result);
    }


}
