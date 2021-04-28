package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.MessageDTO;
import com.jugu.www.pcbonlinev2.domain.dto.MessageQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.MessageDO;
import com.jugu.www.pcbonlinev2.domain.vo.MessageVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.MessageService;
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
 * 系统消息通知接口
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/message")
@Validated
@Slf4j
@Api(value = "信息消息管理", tags = {"信息消息controller"}, protocols = "http, https", hidden = true)
public class MessageController extends BasicController<MessageDO,MessageDTO>{

    @Autowired
    private MessageService messageService;

    @ApiOperation(
            value = "新增信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "messageDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = MessageDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    @Ignore
    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody MessageDTO messageDTO) {

        if (messageService.save(conversionDO(new MessageDO(),messageDTO))){
            return ResponseResult.success("新增成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }
    }

    /**
     * 标记消息为已读
     * @param id id
     * @param messageDTO 消息对象
     * @return
     */
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
                    name = "messageDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = MessageDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    @Ignore
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody MessageDTO messageDTO){

        MessageDO messageDO = conversionDO(new MessageDO(),messageDTO);
        // TODO 赋值id

        if (messageService.updateById(messageDO)){
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
    @Ignore
    public ResponseResult delete(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id){
        if (messageService.removeById(id)){
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
                    dataTypeClass = MessageQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    @Ignore
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated MessageQueryDTO query){
        //构造查询条件
        PageQuery<MessageQueryDTO, MessageDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<MessageDTO>> listPageResult = messageService.queryPage(pageQuery);

        //转化VO
        List<MessageVO> messageVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(MessageDTO -> {
                    MessageVO vo = new MessageVO();
                    BeanUtils.copyProperties(MessageDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<MessageVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(messageVOS);

        return ResponseResult.success(result);
    }

    /**
     * 查询用户系统消息
     */
    @RequestMapping("/queryCurrUserMsg")
    public ResponseResult queryCurrUserMsg() {
        List<MessageDO> messageDOList = messageService.list(new QueryWrapper<MessageDO>().eq("receiveUser", getUserId()).eq("isread", 0));

        List<MessageVO> result = Optional.ofNullable(messageDOList)
                .map(List::stream)
                .orElse(Stream.empty())
                .map(MessageDO -> {
                    MessageVO vo = new MessageVO();
                    BeanUtils.copyProperties(MessageDO, vo);

                    return vo;
                })
                .collect(Collectors.toList());
        return ResponseResult.success(result);
    }

    /**
     * 标记消息为已读
     * @param id 消息id
     */
    @PutMapping("/readMsg/{id}")
    public ResponseResult readMsg(@NotNull(message = "消息id不能为空！") @PathVariable("id") Integer id){
        MessageDO messageDO = new MessageDO();
        messageDO.setId(id);
        messageDO.setIsread(1);

        if (messageService.updateById(messageDO)){
           return ResponseResult.success("更新成功");
        }else{
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }
    }


}
