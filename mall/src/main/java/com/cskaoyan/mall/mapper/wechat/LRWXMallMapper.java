package com.cskaoyan.mall.mapper.wechat;

import com.cskaoyan.mall.bean.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LRWXMallMapper {

    List<Category> queryCategoryList();

    Category queryFirstLevel1Category();

    List<Category> queryCurrentSubCategory(Integer pid);

    @Select("select count(*) from cskaoyan_mall_goods where deleted = false")
    Integer queryGoodsCount();
}
