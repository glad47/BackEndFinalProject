package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.common.Result;
import com.jugu.www.pcbonlinev2.domain.dto.*;
import com.jugu.www.pcbonlinev2.domain.dto.order.*;
import com.jugu.www.pcbonlinev2.domain.entity.OrderDO;
import com.jugu.www.pcbonlinev2.domain.vo.InvoiceInfoVO;
import com.jugu.www.pcbonlinev2.domain.vo.OrderVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.OrderService;
import com.jugu.www.pcbonlinev2.validator.group.UpdateValidationGroup;
import io.github.yedaxia.apidocs.ApiDoc;
import io.github.yedaxia.apidocs.Ignore;
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
 * 订单相关接口
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

    /**
     * 下单接口
     * @param orderSaveDTO
     * @return
     */
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
    @ApiDoc(result = ResponseResult.class)
    public ResponseResult<QuoteInfo> save(@Validated @RequestBody OrderSaveDTO orderSaveDTO) {
        Result result = orderService.saveOrder(orderSaveDTO);
        if (result.isSuccess()){
            return ResponseResult.success(result.getQuoteInfo());
        }else {
            return ResponseResult.failure(ErrorCodeEnum.INSERT_FAILURE);
        }

    }

    /**
     * 修改订单
     * @param id
     * @param orderDTO
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
    @Ignore
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
            value = "删除订单",
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
    @Ignore
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

    /**
     * 创建系统订单编号及计算基本价格信息和优惠券信息
     * @param toPaymentParameterDTO 去支付页面的封装参数对象
     * @return
     */
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

    /**
     * 支付后回调接口
     * @param paymentParameterDTO 支付后回调参数
     * @return
     */
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
        Result result = orderService.createOrder(paymentParameterDTO);
        if (result.isSuccess()){
            return ResponseResult.success(result.getId());
        }
        return ResponseResult.failure("3333",result.getErrorMsg());
    }

    /**
     * 发票信息查询接口
     * @param orderId 订单id
     * @return
     */
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

    /**
     * 使用信用卡支付
     * @param cardPaymentDTO 信用卡支付对象
     * @return
     */
    @PostMapping("/cardPayment")
    @Ignore
    public ResponseResult paymentCard(@RequestBody CardPaymentDTO cardPaymentDTO){
        Result result = orderService.payCard(cardPaymentDTO);
        if (result.isSuccess()){
            return ResponseResult.success("pay success");
        }else {
            return ResponseResult.failure("3333",result.getErrorMsg());
        }
    }


    //@PostMapping("/auditOrderDetails")
    //public ResponseResult auditOrder(@Validated @RequestBody List<OrderDetails> orderDetailsList){
    //    if (orderService.auditOrderDetails(orderDetailsList)){
    //        return ResponseResult.success("操作成功");
    //    }else {
    //        return ResponseResult.failure(ErrorCodeEnum.SYSTEM_ERROR);
    //    }
    //}

    /**
     * 查询订单详情，支持所有报价类型
     * @param orderIds
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/orderItemByIds")
    //@PostMapping("/itemByIds")
    public  ResponseResult queryOrderItemByIds(@RequestBody OrderIds orderIds){
        QuoteInfoList quoteInfoList = orderService.queryQuoteItemByIds(orderIds);
        return ResponseResult.success(quoteInfoList);
    }

}
