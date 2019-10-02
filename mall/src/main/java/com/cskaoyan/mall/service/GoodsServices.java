package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.SteveGoods;

import java.util.List;

/**
 * @author Steve
 * @date 2019/9/30-20:28
 */
public interface GoodsServices {
    List<Goods> queryGoods(SteveGoods steveGoods);
    long queryGoodsNum(SteveGoods steveGoods);
}
