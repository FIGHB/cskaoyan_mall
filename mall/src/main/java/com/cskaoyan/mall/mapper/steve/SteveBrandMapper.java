package com.cskaoyan.mall.mapper.steve;

import com.cskaoyan.mall.vo.steve.CategoryChildren;
import com.cskaoyan.mall.vo.steve.ForCategory;
import com.cskaoyan.mall.vo.steve.SteveBrand;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/1-21:06
 */
@Repository
public interface SteveBrandMapper {

    @Select("select name as label,id as value from cskaoyan_mall_brand")
    List<SteveBrand> queryBrand();

    List<ForCategory> queryCategory(int pid);

    List<CategoryChildren> queryCategoryChildren(String id);
}
