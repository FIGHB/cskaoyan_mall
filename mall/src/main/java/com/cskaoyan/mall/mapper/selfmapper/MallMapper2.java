package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Category;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MallMapper2 {

    @Select("select id,`desc`,icon_url iconUrl,keywords,level,name,pic_url picUrl from cskaoyan_mall_category where pid = #{id}")
    List<Map<String, Object>> selectCategoryListByPid(int id);

    List<Category> selectSimpleCategoryList();

}
