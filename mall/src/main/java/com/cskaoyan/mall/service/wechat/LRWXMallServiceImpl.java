package com.cskaoyan.mall.service.wechat;


import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.mapper.wechat.LRWXMallMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    @Autowired
    KeywordMapper keywordMapper;

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

    @Override
    public Map querySearchIndex(String username) {
        HashMap<Object, Object> data = new HashMap<>();
        data.put("defaultKeyword", lrwxMallMapper.queryDefaultKeyword());
        if(username == null || "".equals(username)) {
            data.put("historyKeywordList", new ArrayList<>());
        } else {
            int userId = lrwxMallMapper.selectUserIdByUserName(username);
            data.put("historyKeywordList", lrwxMallMapper.queryHistoryKeywordList(userId));
        }
        data.put("hotKeywordList", lrwxMallMapper.queryHotKeywordList());
        return data;
    }

    @Override
    public List<String> querySearchHelper(String keyword) {
        String keyword2 = "%" + keyword + "%";
        return lrwxMallMapper.querySearchHelper(keyword2);
    }

    @Override
    public boolean deleteSearchHistory(String username) {
        int userId = lrwxMallMapper.queryUserIdByUserName(username);
        int flag = lrwxMallMapper.deleteSearchHistory(userId);
        return flag > 0;
    }
}
