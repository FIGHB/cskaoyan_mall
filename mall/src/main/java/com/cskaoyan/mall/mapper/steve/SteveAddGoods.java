package com.cskaoyan.mall.mapper.steve;

import com.cskaoyan.mall.vo.steve.GoodsAttributeForAddGoods;
import com.cskaoyan.mall.vo.steve.GoodsForAddGoods;
import com.cskaoyan.mall.vo.steve.GoodsProductForAddGoods;
import com.cskaoyan.mall.vo.steve.GoodsSpecificationForAddGoods;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * @author Steve
 * @date 2019/10/2-15:15
 */
@Component
public interface SteveAddGoods {

    void insertGoodsAttributes(GoodsAttributeForAddGoods attributes);

    void insertGoods(GoodsForAddGoods goods);

    void insertProducts(GoodsProductForAddGoods products);

    void insertGoodsSpecifications(GoodsSpecificationForAddGoods specifications);

    void deleteGoodsById(@Param("id") Integer id);
}
