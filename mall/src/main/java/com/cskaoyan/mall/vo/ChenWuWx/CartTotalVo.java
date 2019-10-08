package com.cskaoyan.mall.vo.ChenWuWx;

import com.cskaoyan.mall.bean.Cart;

import java.util.List;

public class CartTotalVo {
    CartTotal cartTotal;
    List<Cart> cartList;

    public CartTotal getCartTotal() {
        return cartTotal;
    }

    public void setCartTotal(CartTotal cartTotal) {
        this.cartTotal = cartTotal;
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
