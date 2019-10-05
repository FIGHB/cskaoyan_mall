package com.cskaoyan.mall.mapper.WXMapper;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.System;
import com.cskaoyan.mall.bean.wxfbean.FloorGoodList;
import com.cskaoyan.mall.bean.wxfbean.GroupOn;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface HomePageMapper {

    int querySystemByKeyName(@Param("keyName")String keyName);

    List<Ad> queryAD();

    List<Brand> queryBrandList(int limit);

    List<Category> queryChannel();

    List<Coupon> queryCouponList();

    List<FloorGoodList> queryFloorGoodList(int floorGoodListNum);

    int queryL2IdByPid(int pid);

    List<Goods> queryGoodsListByL2Id(@Param("l2Id") int l2Id,@Param("limit") int limit);

    List<GroupOn> queryGroupOnList();

    Goods queryGoodById(int goodsId);

    List<Goods> queryHotGoodsList(int limit);

    List<Goods> queryNewGoodsList(int limit);

    List<Topic> queryTopicList(int limit);

    Category queryCategoryById(int id);

    List<Category> queryCategoryByPid(Integer pid);

    List<Brand> queryBrands();

    int queryTotolOfBrand();

    Brand queryBrandById(int id);
}
