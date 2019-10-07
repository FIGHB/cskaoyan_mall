package com.cskaoyan.mall.service.wechat;


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
}
