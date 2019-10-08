package com.cskaoyan.mall.service.wechat;


import com.cskaoyan.mall.bean.Cart;
import com.cskaoyan.mall.vo.BaseRespVo;

import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
public interface LRWXMallService {
    Map queryCatalogIndex();

    Integer queryGoodsCount();

    Map querySearchIndex(String username);

    List<String> querySearchHelper(String keyword);

    boolean deleteSearchHistory(String username);

    Map queryUserIndex(String username);


    /**
     * 查询当前用户拥有的优惠券
     *
     * @param username
     * @param status
     * @param page
     * @param size
     * @return
     */
    Map queryMyCouponList(String username, int status, int page, int size);

    String receiveCoupon(String username, Integer couponId);

    Map queryCouponList(int page, int size);


    Integer getGoodsCount(String username);

    //如果返回空表示没有问题,反之返回错误信息
    String fastAddCart(Cart cart, String username);

    int getUserIdByUsername(String username);

    Map checkoutCart(int userId, Integer cartId, Integer addressId, Integer couponId, Integer grouponRulesId);

    int queryCartId(int userId);

    Object getCartIndex(int userId);

    Object getOrderList(int userId, int showType, int page, int size);
}
