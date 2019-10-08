package com.cskaoyan.mall.mapper.GuoMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

public interface GuoGoodsProductMapper {

    @Update("update cskaoyan_mall_goods_product set number=#{number} where id=#{productId}")
    int updateNumberByProductId(@Param("productId") Integer productId,@Param("number") Integer number);

    @Select("select number from cskaoyan_mall_goods_product where id=#{productId}")
    int getProductNumberById(@Param("productId") Integer productId);
}
