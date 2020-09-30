package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.*;
import com.jugu.www.pcbonlinev2.domain.dto.order.ToPaymentParameterDTO;
import com.jugu.www.pcbonlinev2.domain.entity.OrderDO;
import com.jugu.www.pcbonlinev2.domain.vo.InvoiceInfoVO;
import com.jugu.www.pcbonlinev2.domain.vo.OrderVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.OrderService;
import com.jugu.www.pcbonlinev2.utils.RedisUtil;
import com.jugu.www.pcbonlinev2.validator.ValidatorUtil;
import com.jugu.www.pcbonlinev2.validator.group.InsertValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.PanelValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.SingleValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.UpdateValidationGroup;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;



/**
 * 订单表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/order")
@Validated
@Slf4j
@Api(value = "订单管理相关接口", tags = {"订单表controller"}, protocols = "http, https", hidden = false)
public class OrderController extends BasicController<OrderDO,OrderDTO>{

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(
            value = "下单接口",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "orderDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = OrderSaveDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping
    public ResponseResult save(@Validated @RequestBody OrderSaveDTO orderSaveDTO) {

        if (orderService.saveOrder(orderSaveDTO)){
            return ResponseResult.success("新增成功");
        }else {
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
                    name = "orderDTO",
                    value = "实体类",
                    required = true,
                    paramType = "body",
                    dataType = "object",
                    dataTypeClass = OrderDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PutMapping("/{id}")
    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody OrderDTO orderDTO){

        OrderDO orderDO = conversionDO(new OrderDO(),orderDTO);
        // TODO 赋值id

        if (orderService.updateById(orderDO)){
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
        if (orderService.removeById(id)){
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
                    dataTypeClass = OrderQueryDTO.class
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult<PageResult> queryPage(@NotNull Integer pageNo, @NotNull Integer pageSize, @Validated OrderQueryDTO query){
        //构造查询条件
        PageQuery<OrderQueryDTO, OrderDO> pageQuery = new PageQuery<>(pageNo, pageSize, query);

        //查询
        PageResult<List<OrderDTO>> listPageResult = orderService.queryPage(pageQuery);

        //转化VO
        List<OrderVO> orderVOS = Optional.ofNullable(listPageResult.getData())
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(OrderDTO -> {
                    OrderVO vo = new OrderVO();
                    BeanUtils.copyProperties(OrderDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        //最终返回结果
        PageResult<List<OrderVO>> result = new PageResult<>();
        BeanUtils.copyProperties(listPageResult, result);
        result.setData(orderVOS);

        return ResponseResult.success(result);
    }


    @ApiOperation(
            value = "创建系统订单编号及计算基本价格信息和优惠券信息",
            notes = "paypal支付后,回调的订单保存接口",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "body",
            value = "去支付页面的封装参数对象",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = ToPaymentParameterDTO.class
    )

    @PostMapping("/createOrderNo")
    public ResponseResult getOrderNoInfo(@Validated @RequestBody ToPaymentParameterDTO toPaymentParameterDTO) {
        toPaymentParameterDTO.setUserId(getUserId());
        PaymentParameterDTO result = orderService.getToPaymentInfo(toPaymentParameterDTO);
        return ResponseResult.success(result);
    }


    @ApiOperation(
            value = "支付后回调接口",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "orderDTO",
            value = "实体类",
            required = true,
            paramType = "body",
            dataType = "object",
            dataTypeClass = PaymentParameterDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/save")
    public ResponseResult createOrder(@Validated @RequestBody PaymentParameterDTO paymentParameterDTO) {
        if (orderService.createOrder(paymentParameterDTO)){
            return ResponseResult.success("支付创建订单成功");
        }
        return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
    }

    @ApiOperation(
            value = "发票信息接口查询",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiImplicitParam(
            name = "orderId",
            value = "订单id",
            required = true,
            paramType = "query",
            dataType = "int"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping("/invoice")
    public ResponseResult invoiceInfo(@NotNull(message = "orderId不能为null") Integer orderId) {
        InvoiceInfoVO invoiceInfoVO = orderService.getInvoiceInfo(orderId);
        return ResponseResult.success(invoiceInfoVO);
    }

}
