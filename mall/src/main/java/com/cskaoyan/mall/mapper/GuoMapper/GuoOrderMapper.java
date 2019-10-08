package com.cskaoyan.mall.mapper.GuoMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface GuoOrderMapper {
    @Update("update cskaoyan_mall_order set deleted=1 where orderId=#{orderId}")
    int updateOrderDeletedById(@Param("orderId") Integer orderId);
}
