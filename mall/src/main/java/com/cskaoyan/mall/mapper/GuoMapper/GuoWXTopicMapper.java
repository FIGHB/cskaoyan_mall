package com.cskaoyan.mall.mapper.GuoMapper;

import com.cskaoyan.mall.bean.Topic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GuoWXTopicMapper {
    @Select("select * from cskaoyan_mall_topic where id=#{id}")
    Topic getTopicById(@Param("id") Integer id);
}
