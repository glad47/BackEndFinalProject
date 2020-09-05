package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.CountryDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CountryQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CountryDO;
import com.jugu.www.pcbonlinev2.domain.vo.CountryAllVO;
import com.jugu.www.pcbonlinev2.domain.vo.CountryVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.CountryService;
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



/**
 *
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:12
 */
@RestController
@RequestMapping("/api/country")
@Validated
@Slf4j
@Api(value = "国家管理", tags = {"国家controller"}, protocols = "http, https", hidden = true)
public class CountryController extends BasicController<CountryDO,CountryDTO>{

    @Autowired
    private CountryService countryService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "countryDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = CountryDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody CountryDTO countryDTO) {
        if (countryService.save(conversionDO(new CountryDO(), countryDTO))){
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
                    name = "countryDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = CountryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody CountryDTO countryDTO){
        CountryDO countryDO = conversionDO(new CountryDO(), countryDTO);
        countryDO.setId(id);
        if (countryService.updateById(countryDO)){
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
        if (countryService.removeById(id)){
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
                    dataTypeClass = CountryQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated CountryQueryDTO query){
        //构造查询条件
        PageQuery<CountryQueryDTO, CountryDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<CountryDTO>> listPageResult = countryService.queryPage(pageQuery);

        //转化VO
        List<CountryVO> countryVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CountryDTO -> {
                    CountryVO vo = new CountryVO();
                    BeanUtils.copyProperties(CountryDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<CountryVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(countryVOS);

        return ResponseResult.success(result);
    }



    @ApiOperation(
            value = "查询所有国家",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping("/all")
    public ResponseResult all(){
        List<CountryDTO> list = countryService.all();
        List<CountryAllVO> result = Optional.ofNullable(list)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CountryDTO -> {
                    CountryAllVO vo = new CountryAllVO();
                    BeanUtils.copyProperties(CountryDTO, vo);

                    return vo;
                })
                .collect(Collectors.toList());
        return ResponseResult.success(result);
    }


}
