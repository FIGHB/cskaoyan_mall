package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.vo.NewGoodAddVO;
import com.cskaoyan.mall.vo.steve.AddGoods;
import com.cskaoyan.mall.vo.steve.ForCategory;
import com.cskaoyan.mall.vo.steve.SteveBrand;
import com.cskaoyan.mall.vo.steve.SteveGoods;

import java.util.List;

/**
 * @author Steve
 * @date 2019/9/30-20:28
 */
public interface GoodsServices {
    List<Goods> queryGoods(SteveGoods steveGoods);
    long queryGoodsNum(SteveGoods steveGoods);

    List<SteveBrand> queryBrand();

    List<ForCategory> queryCategory();

    int addGoods(NewGoodAddVO newGoodAddVO);

    void deleteGoodsById(Integer id);

    //int updateGoods(NewGoodAddVO newGoodAddVO);
}
