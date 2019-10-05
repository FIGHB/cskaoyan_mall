package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.steve.BrandListVo;
import com.cskaoyan.mall.vo.steve.WeChatCategoryVo;
import com.cskaoyan.mall.vo.steve.WeChatGoodsReceiveData;
import com.cskaoyan.mall.vo.steve.WechatGoodsList;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @author Steve
 * @date 2019/10/5-15:28
 */
@Service
public class WeChatGoodsServicesImpl implements WechatGoodsServices {
    @Autowired
    SearchHistoryMapper searchHistoryMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GoodsMapper goodsMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    BrandMapper brandMapper;

    @Override
    public WechatGoodsList queryWeChatGoodsList(WeChatGoodsReceiveData weChatGoodsReceiveData, String username) {
        WechatGoodsList wechatGoodsList = new WechatGoodsList();

        //搜索的时候显示的
        if (weChatGoodsReceiveData.getCategoryId() != null) {
            if (weChatGoodsReceiveData.getCategoryId() == 0) {
                //查两 goods 和 category 同时写入一个searchhistory
                //第一部分 insert searchhistory
                SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
                SearchHistoryExample.Criteria criteria = searchHistoryExample.createCriteria();

                SearchHistory searchHistory = new SearchHistory();
                searchHistory.setKeyword(weChatGoodsReceiveData.getKeyword());
                searchHistory.setAddTime(new Date());
                //根据前面的username查出userid
                UserExample userExample = new UserExample();
                UserExample.Criteria criteriaUser = userExample.createCriteria();
                criteriaUser.andUsernameEqualTo(username);
                List<User> userList = userMapper.selectByExample(userExample);
                User userInsert = null;
                if (!userList.isEmpty()) {
                    userInsert = userList.get(0);
                    searchHistory.setUserId(userInsert.getId());
                }

                searchHistoryMapper.insert(searchHistory);

                //第二部分 查询goods
                GoodsExample goodsExample = new GoodsExample();
                GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                criteria.andKeywordEqualTo(weChatGoodsReceiveData.getKeyword());
                PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                        weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                long count = goodsPageInfo.getTotal();
                wechatGoodsList.setCount(count);
                wechatGoodsList.setGoodsList(goodsList);


                //第三部分 根据goods 查询category
                HashSet<Integer> set = new HashSet<>();
                List<Category> categoryList = null;
                for (Goods goods : goodsList) {
                    Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
                    //将id放到set中,去重以后,然后遍历set,查category
                    set.add(category.getId());
                }
                for (Integer categoryId : set) {
                    Category category = categoryMapper.selectByPrimaryKey(categoryId);
                    categoryList.add(category);
                }
                wechatGoodsList.setFilterCategoryList(categoryList);

                return wechatGoodsList;
            } else if (weChatGoodsReceiveData.getCategoryId() != 0
                    && weChatGoodsReceiveData.getSort() != null) {
                //搜索的时候,点击分类,这时候的分类不是不是0
                //第二部分 查询goods
                GoodsExample goodsExample = new GoodsExample();
                GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                criteriaGoods.andCategoryIdEqualTo(weChatGoodsReceiveData.getCategoryId());
                PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                        weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                long count = goodsPageInfo.getTotal();
                wechatGoodsList.setCount(count);
                wechatGoodsList.setGoodsList(goodsList);

                //第三部分 根据goods 查询category 这里这部分和上面的一样 不用动
                HashSet<Integer> set = new HashSet<>();
                List<Category> categoryList = null;
                for (Goods goods : goodsList) {
                    Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
                    //将id放到set中,去重以后,然后遍历set,查category
                    set.add(category.getId());
                }
                for (Integer categoryId : set) {
                    Category category = categoryMapper.selectByPrimaryKey(categoryId);
                    categoryList.add(category);
                }
                wechatGoodsList.setFilterCategoryList(categoryList);
                wechatGoodsList.setCount(count);
                wechatGoodsList.setGoodsList(goodsList);
                return wechatGoodsList;
            } else { //只有三个参数的时候 有goodsCategory
                GoodsExample goodsExample = new GoodsExample();
                GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                criteriaGoods.andCategoryIdEqualTo(weChatGoodsReceiveData.getCategoryId());
                PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize());
                List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);

                PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                long count = goodsPageInfo.getTotal();
                wechatGoodsList.setCount(count);
                wechatGoodsList.setGoodsList(goodsList);

                //第三部分 根据category 查询category 这里这部分和上面的一样 不用动
                Category category = categoryMapper.selectByPrimaryKey(weChatGoodsReceiveData.getCategoryId());
                CategoryExample categoryExample = new CategoryExample();
                CategoryExample.Criteria criteria = categoryExample.createCriteria();
                criteria.andPidEqualTo(category.getPid());

                List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
                wechatGoodsList.setGoodsList(goodsList);
                wechatGoodsList.setCount(count);
                wechatGoodsList.setFilterCategoryList(categoryList);
                return wechatGoodsList;
            }
        } else {
            GoodsExample goodsExample = new GoodsExample();
            GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
            criteriaGoods.andBrandIdEqualTo(weChatGoodsReceiveData.getBrandId());

            PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize());
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
            long count = goodsPageInfo.getTotal();
            wechatGoodsList.setCount(count);
            wechatGoodsList.setGoodsList(goodsList);

            //第三部分 根据category 查询category 这里这部分和上面的一样 不用动
            HashSet<Integer> set = new HashSet<>();
            List<Category> categoryList = null;
            for (Goods goods : goodsList) {
                Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
                //将id放到set中,去重以后,然后遍历set,查category
                set.add(category.getId());
            }

            if (set.size() == 0) {
                for (Integer categoryId : set) {
                    Category category = categoryMapper.selectByPrimaryKey(categoryId);
                    categoryList.add(category);
                }
            }
            wechatGoodsList.setGoodsList(goodsList);
            wechatGoodsList.setCount(count);
            wechatGoodsList.setFilterCategoryList(categoryList);
            return wechatGoodsList;
        }
    }

    @Override
    public WeChatCategoryVo queryCategoryList(int id) {
        WeChatCategoryVo weChatCategoryVo = new WeChatCategoryVo();
        //首先查出pid
        Category category = categoryMapper.selectByPrimaryKey(id);
        System.out.println(category);
        int pid = category.getPid();
        if (pid == 0) {//说明是第一次进来
            CategoryExample categoryExample = new CategoryExample();
            CategoryExample.Criteria criteria = categoryExample.createCriteria();
            criteria.andPidEqualTo(id);
            List<Category> brathorCategory = categoryMapper.selectByExample(categoryExample);
            weChatCategoryVo.setBrotherCategory(brathorCategory);

            if (!brathorCategory.isEmpty()) {
                Category currentCategory = brathorCategory.get(0);
                weChatCategoryVo.setCurrentCategory(currentCategory);
            }

            Category parentCategory = categoryMapper.selectByPrimaryKey(id);
            weChatCategoryVo.setParentCategory(parentCategory);
            return weChatCategoryVo;
        } else { //点击兄弟目录
            CategoryExample categoryExample = new CategoryExample();
            CategoryExample.Criteria criteria = categoryExample.createCriteria();
            criteria.andPidEqualTo(id);
            List<Category> brathorCategory = categoryMapper.selectByExample(categoryExample);
            weChatCategoryVo.setBrotherCategory(brathorCategory);

            Category currentCategory = categoryMapper.selectByPrimaryKey(id);
            weChatCategoryVo.setCurrentCategory(currentCategory);

            Category parentCategory = categoryMapper.selectByPrimaryKey(pid);
            weChatCategoryVo.setParentCategory(parentCategory);
            return weChatCategoryVo;
        }
    }

}
