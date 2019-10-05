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
}
