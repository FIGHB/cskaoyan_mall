package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Region;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface GuoRegionMapper {
    @Select("select * from cskaoyan_mall_region where id=#{id}")
    Region getRegionById(@Param("id") int id);
}
