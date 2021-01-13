package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.OrderAmountDTO;
import com.jugu.www.pcbonlinev2.service.GraphsStatService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 图形统计controller
 */
@RestController
@Validated
@RequestMapping("/api/stat")
@Api(value = "图形统计接口", tags = {"统计controller"}, protocols = "http, https", hidden = false)
public class GraphsStatController {

    @Autowired
    private GraphsStatService graphsStatService;

    @ApiOperation(
            value = "统计各类型订单各个月份销售额",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "currYears",
                    value = "年",
                    required = true,
                    paramType = "path",
                    dataType = "string"
            )
    })
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping("/orderAmount")
    public ResponseResult<List<OrderAmountDTO>> queryOrderCount(@NotNull(message = "年份不能为空!") String currYears){
        return ResponseResult.success(graphsStatService.statOrderSaleByCurrYear(currYears));
    }
}
