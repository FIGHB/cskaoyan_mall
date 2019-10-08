package com.cskaoyan.mall.mapper.wechat;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDeleteMapper {
    @Update("UPDATE cskaoyan_mall_order SET deleted = #{delete} WHERE id = #{id}")
    int updateStateOfDelete(@Param("id") int id, @Param("delete") boolean delete);
}
