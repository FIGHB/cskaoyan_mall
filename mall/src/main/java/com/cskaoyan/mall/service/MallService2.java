package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Category;

import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
public interface MallService2 {
    List<Map<String, Object>> getCategoryList();

    List<Category> getSimpleCategoryList();
}
