package com.cskaoyan.mall.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface CommentMapperGuo {
    @Update("update cskaoyan_mall_comment set deleted=1 where id=#{id}")
    int deleteCommentById(@Param("id") int id);
}
