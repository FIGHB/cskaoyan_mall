package com.cskaoyan.mall.mapper.WXMapper;

import com.cskaoyan.mall.bean.OrderGoods;

import java.util.List;

public interface WxfOrderMapper {
    int deleteByOrderId(OrderGoods orderGoods);

    int deleteById(OrderGoods orderGoods);

    List<Integer> queryproductId(OrderGoods orderGoods);
}
