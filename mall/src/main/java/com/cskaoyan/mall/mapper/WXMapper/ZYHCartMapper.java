package com.cskaoyan.mall.mapper.WXMapper;

import com.cskaoyan.mall.bean.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ZYHCartMapper {
    void updateCartChecked(@Param("productId") int productId,@Param("isChecked") Boolean isChecked);

    List<Cart> queryCartListByUserId(int userId);

    void updateCart(@Param("id") int id, @Param("number") int number);

    void deleteCart(int productId);

}
