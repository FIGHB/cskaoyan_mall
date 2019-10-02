package com.cskaoyan.mall.mapper.selfmapper;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.springframework.stereotype.Repository;

/**
 * @author 李锐
 */
@Repository
public interface LiRuiHomepageMapper {

    @Select("select count(*) from cskaoyan_mall_goods")
    String countGoodsTotal();

    @Select("select count(*) from cskaoyan_mall_order")
    String countOrderTotal();

    @Select("select count(*) from cskaoyan_mall_goods_product")
    String countProductTotal();
    @Select("select count(*) from cskaoyan_mall_user")
    String countUserTotal();
}
