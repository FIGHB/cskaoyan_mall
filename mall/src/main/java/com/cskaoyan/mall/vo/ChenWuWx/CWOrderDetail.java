package com.cskaoyan.mall.vo.ChenWuWx;

import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;

public class CWOrderDetail {
    List<OrderGoods> orderGoods;
    OrderInfo orderInfo;//取材自order信息

    public List<OrderGoods> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGoods> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
