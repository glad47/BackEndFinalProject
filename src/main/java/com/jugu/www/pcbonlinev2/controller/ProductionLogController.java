package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.ProductionLogDTO;
import com.jugu.www.pcbonlinev2.domain.entity.ProductionLogDO;
import com.jugu.www.pcbonlinev2.domain.vo.ProductionLogVO;
import com.jugu.www.pcbonlinev2.service.ProductionLogService;
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
 * 生产日志
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-09-29 15:45:00
 */
@RestController
@RequestMapping("/api/productionlog")
@Validated
@Slf4j
@Api(value = "生产日志管理", tags = {"生产日志controller"}, protocols = "http, https", hidden = false)
public class ProductionLogController extends BasicController<ProductionLogDO,ProductionLogDTO>{

    @Autowired
    private ProductionLogService productionLogService;

    @ApiOperation(
            value = "查询生产日志信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )

    @ApiImplicitParam(
            name = "productId",
            value = "对应pcb的id",
            required = true,
            paramType = "query",
            dataType = "int"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult queryPage(@NotNull(message = "productId不能为null") Integer productId){
        List<ProductionLogDO> data = productionLogService.list(new QueryWrapper<ProductionLogDO>().eq("product_id",productId));
        //转化VO
        List<ProductionLogVO> productionLogVOS = Optional.ofNullable(data)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(da -> {
                    ProductionLogVO vo = new ProductionLogVO();
                    BeanUtils.copyProperties(da, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        return ResponseResult.success(productionLogVOS);
    }


}
