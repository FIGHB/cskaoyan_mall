package com.cskaoyan.mall.mapper.WXMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface WxfProductMapper {
    @Update("update cskaoyan_mall_goods_product set number = number + 1 where id = #{productId}")
    int updateStorage(@Param("productId") int productId);
}
