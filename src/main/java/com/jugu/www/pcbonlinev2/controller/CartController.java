/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: CartController
 * Author:   zhoulei
 * Date:     2021/7/12 7:59 PM
 * Description: 购物车
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.controller;

import com.jugu.www.pcbonlinev2.domain.common.ResponseResult;
import com.jugu.www.pcbonlinev2.domain.vo.CartListVO;
import com.jugu.www.pcbonlinev2.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 购物车管理接口
 *
 * @author zhoulei
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/cart")
@Api(
        value = "购物车管理Controller",
        tags = {"购物车管理Controller"},
        produces = "http, https",
        hidden = false
)
public class CartController extends BasicController{
    //@Autowired
    //private QuoteService quoteService;
    //
    //@Autowired
    //private SmlStencilService smlStencilService;
    //
    //@Autowired
    //private AssemblyService assemblyService;øø


    @Autowired
    private CartService cartService;

    /**
     * 查询购物车列表
     * @return
     */
    @PostMapping(value = "/list")
    public ResponseResult<List<CartListVO>> cartList(){
        List<CartListVO> cartListVOS = cartService.cartList(getUserId());

        return ResponseResult.success(cartListVOS);
    }


    /**
     * 查询购物车列表数量
     * @return
     */
    @PostMapping(value = "/count")
    public ResponseResult<Integer> cartCount(){
        return ResponseResult.success(cartService.cartCount(getUserId()));
    }
}
