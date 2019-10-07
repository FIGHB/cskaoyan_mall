package com.cskaoyan.mall.service.steve;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.steve.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.lang.System;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
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

    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    IssueMapper issueMapper;

    @Autowired
    GoodsProductMapper goodsProductMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public WechatGoodsList queryWeChatGoodsList(WeChatGoodsReceiveData weChatGoodsReceiveData, String username) {
        WechatGoodsList wechatGoodsList = new WechatGoodsList();

        //搜索的时候显示的 当有isHot和isNew的时候,也要放在这个里面
        if (weChatGoodsReceiveData.getCategoryId() != null) {
            if (weChatGoodsReceiveData.getCategoryId().equals(0)) {
                if (weChatGoodsReceiveData.getIsNew()) {
                    //第二部分 查询goods
                    GoodsExample goodsExample = new GoodsExample();
                    GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                    criteriaGoods.andIsNewEqualTo(weChatGoodsReceiveData.getIsNew());
                    PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                            weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                    List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                    System.out.println(goodsList);
                    PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                    long count = goodsPageInfo.getTotal();
                    wechatGoodsList.setCount(count);
                    wechatGoodsList.setGoodsList(goodsList);

                    //第三部分 根据goods 查询category
                    HashSet<Integer> set = new HashSet<>();
                    List<Category> categoryList = new ArrayList<>();
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
                } else if (weChatGoodsReceiveData.getIsHot()) {
                    //第二部分 查询goods
                    GoodsExample goodsExample = new GoodsExample();
                    GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                    criteriaGoods.andIsHotEqualTo(weChatGoodsReceiveData.getIsHot());
                    PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                            weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                    List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                    PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                    long count = goodsPageInfo.getTotal();
                    wechatGoodsList.setCount(count);
                    wechatGoodsList.setGoodsList(goodsList);

                    //第三部分 根据goods 查询category
                    HashSet<Integer> set = new HashSet<>();
                    List<Category> categoryList = new ArrayList<>();
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
                } else {
                    if(username == null){
                        //第二部分 查询goods
                        GoodsExample goodsExample = new GoodsExample();
                        GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                        criteriaGoods.andNameLike("%" + weChatGoodsReceiveData.getKeyword() + "%");
                        PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                                weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                        long count = goodsPageInfo.getTotal();
                        wechatGoodsList.setCount(count);
                        wechatGoodsList.setGoodsList(goodsList);

                        //第三部分 根据goods 查询category
                        HashSet<Integer> set = new HashSet<>();
                        List<Category> categoryList = new ArrayList<>();
                        for (Goods goods : goodsList) {
                            Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
                            //将id放到set中,去重以后,然后遍历set,查category
                            set.add(category.getId());
                        }
                        System.out.println(set);
                        if (!set.isEmpty()) {
                            for (Integer categoryId : set) {
                                Category category = categoryMapper.selectByPrimaryKey(categoryId);
                                categoryList.add(category);
                            }
                        }
                        wechatGoodsList.setFilterCategoryList(categoryList);

                        return wechatGoodsList;
                    }else {
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
                        System.out.println(userList);
                        if (!userList.isEmpty()) {
                            userInsert = userList.get(0);
                            searchHistory.setUserId(userInsert.getId());
                        }
                        searchHistory.setFrom("wx");
                        searchHistory.setUpdateTime(new Date());
                        searchHistory.setDeleted(false);
                        searchHistoryMapper.insert(searchHistory);

                        //第二部分 查询goods
                        GoodsExample goodsExample = new GoodsExample();
                        GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                        criteriaGoods.andNameLike("%" + weChatGoodsReceiveData.getKeyword() + "%");
                        PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                                weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                        PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                        long count = goodsPageInfo.getTotal();
                        wechatGoodsList.setCount(count);
                        wechatGoodsList.setGoodsList(goodsList);


                        //第三部分 根据goods 查询category
                        HashSet<Integer> set = new HashSet<>();
                        List<Category> categoryList = new ArrayList<>();
                        for (Goods goods : goodsList) {
                            Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
                            //将id放到set中,去重以后,然后遍历set,查category
                            set.add(category.getId());
                        }
                        System.out.println(set);
                        if (!set.isEmpty()) {
                            for (Integer categoryId : set) {
                                Category category = categoryMapper.selectByPrimaryKey(categoryId);
                                categoryList.add(category);
                            }
                        }
                        wechatGoodsList.setFilterCategoryList(categoryList);

                        return wechatGoodsList;
                    }
                }
            } else if (weChatGoodsReceiveData.getCategoryId() != 0
                    && weChatGoodsReceiveData.getSort() != null) {
                if (weChatGoodsReceiveData.getIsNew()){
                    //第二部分 查询goods
                    GoodsExample goodsExample = new GoodsExample();
                    GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                    criteriaGoods.andCategoryIdEqualTo(weChatGoodsReceiveData.getCategoryId()).andIsNewEqualTo(weChatGoodsReceiveData.getIsNew());
                    PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                            weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                    List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                    PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                    long count = goodsPageInfo.getTotal();
                    wechatGoodsList.setCount(count);
                    wechatGoodsList.setGoodsList(goodsList);

                    //第三部分 根据goods 查询category 这里这部分和上面的一样 不用动
                    HashSet<Integer> set = new HashSet<>();
                    List<Category> categoryList = new ArrayList<>();
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
                }else if (weChatGoodsReceiveData.getIsHot()){
                    GoodsExample goodsExample = new GoodsExample();
                    GoodsExample.Criteria criteriaGoods = goodsExample.createCriteria();
                    criteriaGoods.andCategoryIdEqualTo(weChatGoodsReceiveData.getCategoryId()).andIsHotEqualTo(weChatGoodsReceiveData.getIsHot());
                    PageHelper.startPage(weChatGoodsReceiveData.getPage(), weChatGoodsReceiveData.getSize(),
                            weChatGoodsReceiveData.getSort() + " " + weChatGoodsReceiveData.getOrder());
                    List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
                    PageInfo<Goods> goodsPageInfo = new PageInfo<>(goodsList);
                    long count = goodsPageInfo.getTotal();
                    wechatGoodsList.setCount(count);
                    wechatGoodsList.setGoodsList(goodsList);

                    //第三部分 根据goods 查询category 这里这部分和上面的一样 不用动
                    HashSet<Integer> set = new HashSet<>();
                    List<Category> categoryList = new ArrayList<>();
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
                }else {
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
                    List<Category> categoryList = new ArrayList<>();
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
                }
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

    @Override
    public WeChatGoodsDetailVo queryGoodsDetail(int id) {
        WeChatGoodsDetailVo weChatGoodsDetailVo = new WeChatGoodsDetailVo();
        Goods goods = goodsMapper.selectByPrimaryKey(id);
        weChatGoodsDetailVo.setInfo(goods);

        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria = goodsAttributeExample.createCriteria();
        criteria.andGoodsIdEqualTo(id);
        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        weChatGoodsDetailVo.setAttribute(goodsAttributes);

        Brand brand = null;
        if (goods.getBrandId() != 0) {
            brand = brandMapper.selectByPrimaryKey(goods.getBrandId());
        }
        weChatGoodsDetailVo.setBrand(brand);

        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria commentExampleCriteria = commentExample.createCriteria();
        commentExampleCriteria.andValueIdEqualTo(goods.getId());
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        PageInfo<Comment> commentPageInfo = new PageInfo<>(commentList);
        long count = commentPageInfo.getTotal();

        WeChatCommentVo weChatCommentVo = new WeChatCommentVo();
        weChatCommentVo.setCount(count);
        weChatCommentVo.setData(commentList);
        weChatGoodsDetailVo.setComment(weChatCommentVo);

        //如果后续有团购的话,这里需要加东西
        List<Groupon> groupons = new ArrayList<>();
        weChatGoodsDetailVo.setGroupon(groupons);

        IssueExample issueExample = new IssueExample();
        List<Issue> issueList = issueMapper.selectByExample(issueExample);
        weChatGoodsDetailVo.setIssue(issueList);

        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria goodsProductExampleCriteria = goodsProductExample.createCriteria();
        goodsProductExampleCriteria.andGoodsIdEqualTo(goods.getId());
        List<GoodsProduct> goodsProductList = goodsProductMapper.selectByExample(goodsProductExample);
        weChatGoodsDetailVo.setProductList(goodsProductList);

        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria goodsSpecificationExampleCriteria = goodsSpecificationExample.createCriteria();
        goodsSpecificationExampleCriteria.andGoodsIdEqualTo(goods.getId());
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);

        //System.out.println(goodsSpecificationList);
        WeChatSpecVo weChatSpecVo = new WeChatSpecVo();
        //根据specification 分组 所以用set
        HashSet<String> set = new HashSet<>();
        for (GoodsSpecification goodsSpecification : goodsSpecificationList) {
            set.add(goodsSpecification.getSpecification());
        }

        List<WeChatSpecVo> specificationList = new ArrayList<>();
        if (!set.isEmpty()) {
            for (String spec : set) {
                GoodsSpecificationExample goodsSpecExample = new GoodsSpecificationExample();
                GoodsSpecificationExample.Criteria goodsSpecExampleCriteria = goodsSpecExample.createCriteria();
                goodsSpecExampleCriteria.andSpecificationEqualTo(spec).andGoodsIdEqualTo(goods.getId());
                List<GoodsSpecification> goodsSpecificationList1 = goodsSpecificationMapper.selectByExample(goodsSpecExample);
                weChatSpecVo.setName(spec);
                weChatSpecVo.setValueList(goodsSpecificationList1);
                System.out.println(weChatSpecVo);
                specificationList.add(weChatSpecVo);
            }
        }
        weChatGoodsDetailVo.setSpecificationList(specificationList);
        weChatGoodsDetailVo.setUserHasCollect(false);

        return weChatGoodsDetailVo;
    }

    @Override
    public WeChataGoodsListVo queryWeChatGoodsForRelated(int id) {
        WeChataGoodsListVo weChataGoodsListVo = new WeChataGoodsListVo();
        Goods goods = goodsMapper.selectByPrimaryKey(id);

        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andCategoryIdEqualTo(goods.getCategoryId());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        weChataGoodsListVo.setGoodsList(goodsList);

        return weChataGoodsListVo;
    }
}
