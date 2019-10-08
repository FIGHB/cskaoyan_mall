package com.cskaoyan.mall.service.ChenWuService;

import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.OrderGoods;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWOrderDetail;
import com.cskaoyan.mall.vo.ChenWuWx.CWOrderListVo;
import com.cskaoyan.mall.vo.ChenWuWx.CWSubmit;
import com.cskaoyan.mall.vo.ChenWuWx.OrderInfo;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {

    int queryOrderIdByUserId(int useId);

    /*CWOrderListVo queryCWOrderListVo(int showType,int page, int size);*/

    void submitOrder(int useId, CWSubmit cwSubmit);

    void submitOrderUseCart(int useId, CWSubmit cwSubmit);

    void InsertStatusValue(int userId, Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId);

    /*此处无用*/
    int insertGoodsPrice(Integer goodsId, BigDecimal goodsprice, Integer productId);


    List<OrderGoods> queryOrderGoodsList(Integer orderId);

    OrderInfo queryOrderInfo(Integer orderId);

    int updateOrderPrepay(Integer orderId);


    void insertOrder();

}
