package com.cskaoyan.mall.vo.MallBean;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.bean.User;

import java.util.List;

public class OrderDetailBean {
    Order order;
    List<OrderGoodsBean> orderGoodsList;
    User user;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderGoodsBean> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoodsBean> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
