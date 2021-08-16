package com.jugu.www.pcbonlinev2.mapper;

import com.jugu.www.pcbonlinev2.domain.vo.CartListVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {
    List<CartListVO> queryCartList(Integer userId);

    Integer queryCartCount(Integer userId);
}
