package com.cskaoyan.mall.mapper;

import com.cskaoyan.mall.bean.GoodsSpecification;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GuoSpecificationMapper {

     List<GoodsSpecification> getSpecificationByGoodsId(@Param("goodsId") Integer id);
}
