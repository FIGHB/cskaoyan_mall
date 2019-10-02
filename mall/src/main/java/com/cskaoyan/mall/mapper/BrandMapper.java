package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.BrandExample;
import com.cskaoyan.mall.bean.Issue;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
@Component
public interface BrandMapper {
    long countByExample(BrandExample example);

    int deleteByExample(BrandExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Brand record);

    int insertSelective(Brand record);

    List<Brand> selectByExample(BrandExample example);

    Brand selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByExample(@Param("record") Brand record, @Param("example") BrandExample example);

    int updateByPrimaryKeySelective(Brand record);

    int updateByPrimaryKey(Brand record);

    List<Brand> queryBrandList(@Param("id") Integer id, @Param("name") String name);

    long queryBrandListTotal(@Param("id") Integer id, @Param("name") String name);

    int insertBrandList(@Param("brand") Brand brand);

    Brand queryBrandById(@Param("id") int id);

    void updateBrand(@Param("brand") Brand brand);

    void deleteBrandById(@Param("id") Integer id);
}