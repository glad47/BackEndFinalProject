package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.ReceiverAddersDTO;
import com.jugu.www.pcbonlinev2.domain.dto.ReceiverAddersQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ReceiverAddersDO;
import com.jugu.www.pcbonlinev2.domain.vo.ReceiverAddersVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.ReceiverAddersService;
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
 * 收货地址
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 16:44:13
 */
@RestController
@RequestMapping("/api/receiveraddress")
@Validated
@Slf4j
@Api(value = "收货地址管理", tags = {"收货地址controller"}, protocols = "http, https", hidden = false)
public class ReceiverAddersController extends BasicController<ReceiverAddersDO,ReceiverAddersDTO>{

    @Autowired
    private ReceiverAddersService receiverAddersService;

    /**
     * 新增收货地址信息
     * @param receiverAddersDTO 收货地址实体类
     */
    @ApiOperation(
            value = "新增收货地址信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "receiverAddersDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = ReceiverAddersDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody ReceiverAddersDTO receiverAddersDTO) {
        if (receiverAddersDTO.getIsDefault() == 1){
            //把当前用户的所有其他地址设置为0
            ReceiverAddersDO update = new ReceiverAddersDO();
            update.setIsDefault(0);
            receiverAddersService.update(update,new QueryWrapper<ReceiverAddersDO>().eq("user_id",getUserId()));
        }
        if (receiverAddersService.save(conversionDO(new ReceiverAddersDO(), receiverAddersDTO))) {
            return ResponseResult.success("新增成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    /**
     * 修改收货地址信息
     * @param id id
     * @param receiverAddersDTO 收货地址实体类
     * @return
     */
    @ApiOperation(
            value = "修改收货地址信息",
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
                    name = "receiverAddersDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = ReceiverAddersDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody ReceiverAddersDTO receiverAddersDTO){
        ReceiverAddersDO receiverAddersDO = conversionDO(new ReceiverAddersDO(), receiverAddersDTO);
        receiverAddersDO.setId(id);
        if (receiverAddersDO.getIsDefault() == 1){
            //把当前用户的所有其他地址设置为0
            ReceiverAddersDO update = new ReceiverAddersDO();
            update.setIsDefault(0);
            receiverAddersService.update(update,new QueryWrapper<ReceiverAddersDO>().eq("user_id",getUserId()));
        }
        if (receiverAddersService.updateById(receiverAddersDO)){
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
        if (receiverAddersService.removeById(id)){
            return ResponseResult.success("删除成功");
        }else {
            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
        }
    }

    /**
     * 查询收货地址
     * @param pageNo 页码
     * @param pageSize 显示多少条
     * @param query 收货地址实体类
     */
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
                    dataTypeClass = ReceiverAddersQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated ReceiverAddersQueryDTO query) {
        query.setUserId(getUserId());
        //构造查询条件
        PageQuery<ReceiverAddersQueryDTO, ReceiverAddersDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<ReceiverAddersDTO>> listPageResult = receiverAddersService.queryPage(pageQuery);

        //转化VO
        List<ReceiverAddersVO> receiverAddersVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(ReceiverAddersDTO -> {
                    ReceiverAddersVO vo = new ReceiverAddersVO();
                    BeanUtils.copyProperties(ReceiverAddersDTO, vo);
                    // TODO: 特殊字段处理
                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<ReceiverAddersVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(receiverAddersVOS);

        return ResponseResult.success(result);
    }

    /**
     * 查询当前用户默认地址信息
     * @return
     */
    @ApiOperation(
            value = "查询当前用户默认地址信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiResponses({
            @ApiResponse(code = 200, message = "操作成功")
    })
    @GetMapping("/curr")
    public ResponseResult currAdders() {
        ReceiverAddersDO addersDO = receiverAddersService.getOne(new QueryWrapper<ReceiverAddersDO>().eq("user_id", getUserId()).eq("is_default", 1));
        if (addersDO == null) {
            return ResponseResult.failure(ErrorCodeEnum.NOT_DEFAULT_ADDRESS);
        }else {
            ReceiverAddersVO vo = new ReceiverAddersVO();
            BeanUtils.copyProperties(addersDO,vo);
            return ResponseResult.success(vo);
        }

    }

}
