package com.cskaoyan.mall.mapper.GuoMapper;

import com.cskaoyan.mall.bean.Region;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GuoWXRegionMapper {
    @Select("select * from cskaoyan_mall_region where pid=#{pid}")
    List<Region> getRegionList(@Param("pid") Integer pid);
}
