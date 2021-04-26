package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteDTO;
import com.jugu.www.pcbonlinev2.domain.dto.QuoteQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.QuoteDO;
import com.jugu.www.pcbonlinev2.domain.vo.QuoteVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.QuoteService;
import com.jugu.www.pcbonlinev2.validator.group.InsertValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.UpdateValidationGroup;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 报价订单接口
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/quote")
@Validated
@Slf4j
@Api(value = "PCB订单管理", tags = {"pcb订单表controller"}, protocols = "http, https")
public class QuoteController extends BasicController<QuoteDO,QuoteDTO>{

    @Autowired
    private QuoteService quoteService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "quoteDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = QuoteDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody QuoteDTO quoteDTO) {

        if (quoteService.save(conversionDO(new QuoteDO(),quoteDTO))){
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
                    name = "quoteDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = QuoteDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody QuoteDTO quoteDTO){

        QuoteDO quoteDO = conversionDO(new QuoteDO(),quoteDTO);
        // TODO 赋值id

        if (quoteService.updateById(quoteDO)){
            return ResponseResult.success("更新成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }

    @ApiOperation(
            value = "删除信息",
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
        if (quoteService.removeById(id)){
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    @ApiOperation(
            value = "查询pcb订单信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "quoteQueryDTO",
            value = "查询封装的对象",
            required = false,
            paramType = "body",
            dataType = "object",
            dataTypeClass = QuoteQueryDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/query")
    public ResponseResult<PageResult> queryPage(@Validated @RequestBody QuoteQueryDTO quoteQueryDTO) {
        //构造查询条件
//        PageQuery<QuoteQueryDTO, QuoteDO> pageQuery = new PageQuery<>(pageNo, pageSize, quoteQueryDTO);

        //查询
//        PageResult<List<QuoteDTO>> listPageResult = quoteService.queryPage(pageQuery);
        List<QuoteDO> data =  quoteService.list(new QueryWrapper<QuoteDO>()
                .eq(!StringUtils.isEmpty(quoteQueryDTO.getStatus()),"status",quoteQueryDTO.getStatus())
                .in(quoteQueryDTO.getStatusList() != null,"status",quoteQueryDTO.getStatusList())
                .eq("user_id",getUserId()));

        //转化VO
        List<QuoteVO> quoteVOS = Optional.ofNullable(data)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(QuoteDTO -> {
                    QuoteVO vo = new QuoteVO();
                    BeanUtils.copyProperties(QuoteDTO, vo);
                    // TODO: 特殊字段处理
                    vo.setOType(1);
                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<QuoteVO>> result = new PageResult<>();
        result.setData(quoteVOS);
        result.setTotal(Integer.valueOf(quoteVOS.size()).longValue());

        return ResponseResult.success(result);
    }



}
