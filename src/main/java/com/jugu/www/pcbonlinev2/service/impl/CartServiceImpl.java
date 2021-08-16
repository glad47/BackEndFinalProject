/*
 * Copyright (C), 2015-2021, XXX有限公司
 * FileName: CartServiceImpl
 * Author:   zhoulei
 * Date:     2021/7/12 9:46 PM
 * Description: 购物车实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.jugu.www.pcbonlinev2.service.impl;

import com.jugu.www.pcbonlinev2.domain.vo.CartListVO;
import com.jugu.www.pcbonlinev2.mapper.CartMapper;
import com.jugu.www.pcbonlinev2.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 〈购物车实现类〉
 *
 * @author zhoulei
 * @since 1.0.0
 */
@Service("cartService")
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<CartListVO> cartList(Integer userId) {
        return cartMapper.queryCartList(userId);
    }

    @Override
    public Integer cartCount(Integer userId) {
        return cartMapper.queryCartCount(userId);
    }
}
