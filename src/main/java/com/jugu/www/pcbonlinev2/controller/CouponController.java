package com.jugu.www.pcbonlinev2.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jugu.www.pcbonlinev2.domain.common.PageQuery;
import com.jugu.www.pcbonlinev2.domain.common.PageResult;
import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.dto.CouponDTO;
import com.jugu.www.pcbonlinev2.domain.dto.CouponQueryDTO;
import com.jugu.www.pcbonlinev2.domain.entity.CouponDO;
import com.jugu.www.pcbonlinev2.domain.vo.CouponVO;
import com.jugu.www.pcbonlinev2.exception.ErrorCodeEnum;
import com.jugu.www.pcbonlinev2.service.CouponService;
import com.jugu.www.pcbonlinev2.utils.GenSerialUtil;
import com.jugu.www.pcbonlinev2.validator.group.InsertValidationGroup;
import com.jugu.www.pcbonlinev2.validator.group.UpdateValidationGroup;
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
 * 优惠券表
 *
 * @author turing
 * @email zlturing@gmail.com
 * @date 2020-07-16 20:50:39
 */
@RestController
@RequestMapping("/api/coupon")
@Validated
@Slf4j
@Api(value = "优惠券表管理", tags = {"优惠券表controller"}, protocols = "http, https", hidden = true)
public class CouponController extends BasicController<CouponDO, CouponDTO> {

    @Autowired
    private CouponService couponService;

//    @ApiOperation(
//            value = "新增信息",
//            notes = "备注",
//            response = ResponseResult.class,
//            httpMethod = "POST"
//    )
//    @ApiImplicitParam(
//            name = "couponDTO",
//            value = "实体类",
//            required = true,
//            paramType = "body",
//            dataType = "object",
//            dataTypeClass = CouponDTO.class
//    )
//    @ApiResponses({
//            @ApiResponse(code = 0, message = "操作成功")
//    })
//    @PostMapping
//    public ResponseResult save(@Validated(InsertValidationGroup.class) @RequestBody CouponDTO couponDTO) {
//
//        if (couponService.save(conversionDO(new CouponDO(),couponDTO))){
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
//                    name = "couponDTO",
//                    value = "实体类",
//                    required = true,
//                    paramType = "body",
//                    dataType = "object",
//                    dataTypeClass = CouponDTO.class
//            )
//    })
//    @ApiResponses({
//            @ApiResponse(code = 0, message = "操作成功")
//    })
//    @PutMapping("/{id}")
//    public ResponseResult update(@NotNull(message = "用户id不能为空！") @PathVariable("id") Integer id, @Validated(UpdateValidationGroup.class) @RequestBody CouponDTO couponDTO){
//
//        CouponDO couponDO = conversionDO(new CouponDO(),couponDTO);
//        // TODO 赋值id
//
//        if (couponService.updateById(couponDO)){
//            return ResponseResult.success("更新成功");
//        }else{
//            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
//        }
//    }
//
//    @ApiOperation(
//            value = "新增信息",
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
//        if (couponService.removeById(id)){
//            return ResponseResult.success("删除成功");
//        }else {
//            return ResponseResult.failure(ErrorCodeEnum.DELETE_FAILURE);
//        }
//    }

    @ApiOperation(
            value = "查询当前用户的所有优惠券信息",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiImplicitParam(
            name = "query",
            value = "查询封装的对象",
            required = false,
            paramType = "query",
            dataType = "object",
            dataTypeClass = CouponQueryDTO.class
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping
    public ResponseResult queryPage(@Validated CouponQueryDTO query) {
        query.setUserId(getUserId());

        //查询
        List<CouponDO> couponDOList = couponService.list(new QueryWrapper<CouponDO>()
                .eq(query.getCouponStatus() != null && query.getCouponStatus() != 3,"coupon_status",query.getCouponStatus())
                .apply(query.getCouponStatus() != null && query.getCouponStatus() == 3,"TO_DAYS(start_time) >= TO_DAYS(NOW()) OR TO_DAYS(end_time) <= TO_DAYS(NOW())")
                .eq(query.getUserId() != null,"user_id",query.getUserId()));


        //转化VO
        List<CouponVO> couponVOS = Optional.ofNullable(couponDOList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .map(CouponDTO -> {
                    CouponVO vo = new CouponVO();
                    BeanUtils.copyProperties(CouponDTO, vo);
                    // TODO: 特殊字段处理

                    return vo;
                })
                .collect(Collectors.toList());

        return ResponseResult.success(couponVOS);
    }


    @ApiOperation(
            value = "生成优惠券",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "GET"
    )
    @ApiImplicitParam(
            name = "flag",
            value = "对应优惠券规则表code_flag字段",
            required = true,
            paramType = "query",
            dataType = "int"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @GetMapping("/generate")
    public ResponseResult generateFiveCode(@NotNull int flag) {
        return ResponseResult.success(GenSerialUtil.generateCode(flag));
    }

    @ApiOperation(
            value = "检验及兑换优惠券",
            notes = "备注",
            response = ResponseResult.class,
            httpMethod = "POST"
    )
    @ApiImplicitParam(
            name = "flag",
            value = "优惠码",
            required = true,
            paramType = "query",
            dataType = "string"
    )
    @ApiResponses({
            @ApiResponse(code = 0, message = "操作成功")
    })
    @PostMapping("/verify")
    public ResponseResult verifyCode(@NotNull String code) {
        if (couponService.verifyCoupon(code, getUserId())) {
            return ResponseResult.success("兑换成功");
        } else {
            return ResponseResult.failure(ErrorCodeEnum.UPDATE_FAILURE);
        }

    }
}
