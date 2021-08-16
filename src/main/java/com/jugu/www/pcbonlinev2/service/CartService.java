package com.jugu.www.pcbonlinev2.service;

import com.jugu.www.pcbonlinev2.domain.vo.CartListVO;

import java.util.List;

public interface CartService {
    List<CartListVO> cartList(Integer userId);

    Integer cartCount(Integer userId);
}
