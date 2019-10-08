package com.cskaoyan.mall.service.ChenWuService;

import com.cskaoyan.mall.vo.ChenWuWx.CWOrderListVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWSubmit;

import java.math.BigDecimal;

public interface OrderService {

    int queryOrderIdByUserId(int useId);

   /* CWOrderListVo queryCWOrderListVo(int page, int size);*/

   /* void submitOrder(int useId, CWSubmit cwSubmit);*/

    void InsertStatusValue(int userId, Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId);

    /*此处无用*/
    int insertGoodsPrice(Integer goodsId, BigDecimal goodsprice, Integer productId);

    //int insertGoodsPrice(Integer goodsId, short number, Integer productId);
}
