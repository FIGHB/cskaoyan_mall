package com.cskaoyan.mall.service.wechat;


import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.bean.wechat.UserCouponBean;
import com.cskaoyan.mall.mapper.CategoryMapper;
import com.cskaoyan.mall.mapper.KeywordMapper;
import com.cskaoyan.mall.mapper.wechat.LRWXMallMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    /**
     * 查询当前登录用户的各订单状态的总数
     * @param username 当前登录用户的用户名
     * @return
     */
    @Override
    public Map queryUserIndex(String username) {
        Map<Object, Object> map = new HashMap<>();
        int userId = lrwxMallMapper.queryUserIdByUserName(username);
        //查询订单状态是已收货的id
        List<Order> orders = lrwxMallMapper.queryOrdersByUserAndStatus(userId, 402);
        int uncomment = 0;
        for (Order order : orders) {
            int commentStatus = lrwxMallMapper.queryCommentStatusByOrderId(order.getId());
            if(commentStatus == 0) {
                uncomment++;
            }
        }
        map.put("uncomment", uncomment);
        map.put("unpaid", lrwxMallMapper.queryOrdersByUserAndStatus(userId, 101).size());
        map.put("unrecv", lrwxMallMapper.queryOrdersByUserAndStatus(userId, 301).size());
        map.put("unship", lrwxMallMapper.queryOrdersByUserAndStatus(userId, 201).size());
        HashMap<Object, Object> order = new HashMap<>();
        order.put("order", map);
        return order;
    }

    @Override
    public Map queryMyCouponList(String username, int status, int page, int size) {
        //获取用户id
        int userId = lrwxMallMapper.queryUserIdByUserName(username);
        //开启分页
        PageHelper.startPage(page, size);
        List<UserCouponBean> userCouponlists = lrwxMallMapper.queryMyCouponListByStatus(userId, status);
        //获取总数
        PageInfo<UserCouponBean> pageInfo = new PageInfo<>(userCouponlists);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("data", userCouponlists);
        map.put("count", pageInfo.getTotal());
        return map;
    }
}
