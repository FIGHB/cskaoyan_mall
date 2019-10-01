package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.GoodsProduct;

import java.util.List;

/**
 * @author Steve
 * @date 2019/9/30-17:15
 */
public interface ProductServices {
    List<GoodsProduct> queryGoodsProduct(int start, int limit, String add_time, String desc);
}
