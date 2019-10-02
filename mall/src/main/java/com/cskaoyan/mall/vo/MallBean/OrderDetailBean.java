package com.cskaoyan.mall.vo.MallBean;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.User;

import java.util.List;

public class OrderDetailBean {
    Order order;
    List<OrderGoodsBean> orderGoods;
    User user;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderGoodsBean> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoodsList(List<OrderGoodsBean> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
