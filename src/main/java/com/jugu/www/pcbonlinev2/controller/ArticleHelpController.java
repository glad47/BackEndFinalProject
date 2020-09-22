package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleHelpDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ArticleHelpQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ArticleHelpDO;
import com.jugu.www.pcbonlinev2.domain.vo.ArticleHelpVO;
import com.jugu.www.pcbonlinev2.service.ArticleHelpService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 帮助主题表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-17 10:29:18
 */
@RestController
@RequestMapping("/api/articlehelp")
@Validated
@Slf4j
@Api(value = "帮助主题表管理", tags = {"帮助主题表controller"}, protocols = "http, https", hidden = false)
public class ArticleHelpController extends BasicController<ArticleHelpDO,ArticleHelpDTO>{

    @Autowired
    private ArticleHelpService articleHelpService;

//    @ApiOperation(
//            value = "新增信息",
//            notes = "备注",
//            response = ResponseResult.class,
//            httpMethod = "POST"
//    )
//    @ApiImplicitParam(
//            name = "articleHelpDTO",
//            value = "实体类",
//            required = true,
//            paramType = "body",
//            dataType = "object",
//            dataTypeClass = ArticleHelpDTO.class
//    )
//    @ApiResponses({
//            @ApiResponse(code = 0, message = "操作成功")
//    })
//    @PostMapping
//    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody ArticleHelpDTO articleHelpDTO) {
//
//        if (articleHelpService.save(conversionDO(new ArticleHelpDO(),articleHelpDTO))){
//            return ResponseResult.success("新增成功");
//        }else{
//            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
//        }
//    }
//
//    @ApiOperation(
//            value = "修改信息",
//            notes = "备注",
//            response = ResponseResult.class,
//            httpMethod = "PUT"
//    )
//    @ApiImplicitParams({
//            @ApiImplicitParam(
//                    name = "id",
//                    value = "主键",
//                    required = true,
//                    paramType = "path",
//                    dataType = "int"
//            ),
//            @ApiImplicitParam(
//                    name = "articleHelpDTO",
//                    value = "实体类",
//                    required = true,
//                    paramType = "body",
//                    dataType = "object",
//                    dataTypeClass = ArticleHelpDTO.class
//            )
//    })
//    @ApiResponses({
//            @ApiResponse(code = 0, message = "操作成功")
//    })
//    @PutMapping("/{id}")
//    public ResponseResult update(@NotNull(message = "id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody ArticleHelpDTO articleHelpDTO){
//
//        ArticleHelpDO articleHelpDO = conversionDO(new ArticleHelpDO(),articleHelpDTO);
//        // TODO 赋值id
//        articleHelpDO.setId(id);
//
//        if (articleHelpService.updateById(articleHelpDO)){
//            return ResponseResult.success("更新成功");
//        }else{
//            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
//        }
//    }
//
//    @ApiOperation(
//            value = "删除信息",
//            notes = "备注",
//            response = ResponseResult.class,
//            httpMethod = "DELETE"
//    )
//    @ApiImplicitParam(
//            name = "id",
//            value = "主键",
//            required = true,
//            paramType = "path",
//            dataType = "int"
//    )
//    @ApiResponses({
//            @ApiResponse(code = 0, message = "操作成功")
//    })
//    @DeleteMapping("/{id}")
//    public ResponseResult delete(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id){
//        if (articleHelpService.removeById(id)){
//            return ResponseResult.success("删除成功");
//        }else {
//            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
//        }
//    }

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
                    dataTypeClass = ArticleHelpQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated ArticleHelpQueryDTO query){
        //构造查询条件
        PageQuery<ArticleHelpQueryDTO, ArticleHelpDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<ArticleHelpDTO>> listPageResult = articleHelpService.queryPage(pageQuery);

        //转化VO
        List<ArticleHelpVO> articleHelpVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ArticleHelpDTO -> {
                    ArticleHelpVO vo = new ArticleHelpVO();
                    BeanUtils.copyProperties(ArticleHelpDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<ArticleHelpVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(articleHelpVOS);

        return ResponseResult.success(result);
    }


}
