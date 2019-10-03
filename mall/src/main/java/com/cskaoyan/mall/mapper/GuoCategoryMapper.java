package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Category;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GuoCategoryMapper {

    @Select("select pid from cskaoyan_mall_category where id=#{id} and deleted=0")
    int getCategoryPidByCategoryId(@Param("id") int id);

    @Select("select * from cskaoyan_mall_category where pid=#{pid}")
    List<Category> getCategorys(@Param("pid") int id);

    @Select("select * from cskaoyan_mall_category where pid!=0")
    List<Category> getCategorysPidNot0();

}
