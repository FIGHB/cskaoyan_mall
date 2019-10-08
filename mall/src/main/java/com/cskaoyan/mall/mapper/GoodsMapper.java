package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface GoodsMapper {
    long countByExample(GoodsExample example);

    int deleteByExample(GoodsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Goods record);

    //steve 2019.10.2
    int steveInsert(@Param("record") Goods record);

    int insertSelective(Goods record);

    List<Goods> selectByExampleWithBLOBs(GoodsExample example);

    List<Goods> selectByExample(GoodsExample example);

    Goods selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExampleWithBLOBs(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByExample(@Param("record") Goods record, @Param("example") GoodsExample example);

    int updateByPrimaryKeySelective(Goods record);

    int updateByPrimaryKeyWithBLOBs(Goods record);

    int updateByPrimaryKey(Goods record);

    void deleteGoodsById(Integer id);

    void insertAttributes(@Param("attributes") List<GoodsAttribute> attributes,@Param("goodId") int goodId);

    void insertGoods(@Param("goods") Goods goods,@Param("id") int id);

    void insertProduct(@Param("products") List<GoodsProduct> products, @Param("goodId") int goodId);

    void insertSpec(@Param("specs") List<GoodsSpecification> specifications,@Param("goodId") int goodId);

}
