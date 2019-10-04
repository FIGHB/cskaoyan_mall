package com.cskaoyan.mall.service.wechat;


import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.wechat.LRWXMallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李锐
 */
@Service
public class LRWXMallServiceImpl implements LRWXMallService {

    @Autowired
    LRWXMallMapper lrwxMallMapper;


    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Map queryCatalogIndex() {
        Map data = new HashMap();
        data.put("categoryList", lrwxMallMapper.queryCategoryList());
        Category category = lrwxMallMapper.queryFirstLevel1Category();
        data.put("currentCategory", category);
        data.put("currentSubCategory", lrwxMallMapper.queryCurrentSubCategory(category.getId()));
        return data;
    }

    @Override
    public Integer queryGoodsCount() {
        return lrwxMallMapper.queryGoodsCount();
    }
}
