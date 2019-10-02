package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.CategoryExample;
import com.cskaoyan.mall.vo.MallBean.CategoryBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    @Select("select id,`desc`,icon_url iconUrl,keywords,level,name,pic_url picUrl from cskaoyan_mall_category where pid = #{id} and deleted = false")
    List<Map<String, Object>> selectCategoryListByPid(int pid);

    List<Map> selectSimpleCategoryList();

    int insertCategory(@Param("category") Category category);

    Category queryCategoryById(@Param("id") int id);

    void deleteCategoryById(@Param("id") Integer id);

    void updateCategory(@Param("category") CategoryBean categoryBean);
}