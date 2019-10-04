package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.utils.ListBean;
import com.cskaoyan.mall.vo.MallBean.CategoryBean;
import com.cskaoyan.mall.vo.MallBean.OrderDetailBean;
import com.cskaoyan.mall.vo.MallBean.RegionBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    IssueMapper issueMapper;
    @Autowired
    KeywordMapper keywordMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public List queryRegionList() {
         List<RegionBean> regions = regionMapper.queryRegionListByType(1);
        for (RegionBean region:regions) {
            List<RegionBean> cityList = regionMapper.queryRegionListByPid(region.getId());
            for (RegionBean city:cityList){
                List<RegionBean> areaList = regionMapper.queryRegionListByPid(city.getId());
                city.setChildren(areaList);
            }
            region.setChildren(cityList);
        }
        return regions;
    }

    @Override
    public ListBean<Brand> queryBrandList(int page, int limit, String sort, String order, Integer id, String name) {
        String orderBy = String.format("%s %s",sort,order);
        PageHelper.startPage(page, limit, orderBy);
        List<Brand> brandList = brandMapper.queryBrandList(id, name);
        long total = brandMapper.queryBrandListTotal(id,name);
        ListBean<Brand> brandListBean = new ListBean<>(brandList, total);
        return brandListBean;
    }

    @Override
    public Brand insertBrandList(Brand brand) {
        Date date = new Date();
        brand.setAddTime(date);
        int result = brandMapper.insertBrandList(brand);
        int id = brand.getId();
        return brandMapper.queryBrandById(id);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        Date date = new Date();
        brand.setUpdateTime(date);
        brandMapper.updateBrand(brand);
        return brandMapper.queryBrandById(brand.getId());
    }

    @Override
    public void deleteBrand(Integer id) {
        brandMapper.deleteBrandById(id);
    }

    @Override
    public ListBean<Order> queryOrderList(int page, int limit, String sort, String order, Integer userId,String orderSn,Short[] orderStatusArray) {
        String orderBy = String.format("%s %s",sort,order);
        PageHelper.startPage(page, limit, orderBy);
        List<Order> orderList = orderMapper.queryOrderList(userId, orderSn, orderStatusArray);
        long total = orderMapper.queryOrderListTotal(userId, orderSn, orderStatusArray);
        ListBean<Order> orderListBean = new ListBean<>(orderList, total);
        return orderListBean;
    }

    @Override
    public OrderDetailBean queryOrderDetail(int id) {
        OrderDetailBean orderDetailBean = new OrderDetailBean();
        Order order = orderMapper.queryOrderById(id);
        orderDetailBean.setOrder(order);
        orderDetailBean.setOrderGoodsList(orderMapper.queryOrderGoodsListByOrderId(id));
        orderDetailBean.setUser(orderMapper.queryUserById(order.getUserId()));
        return orderDetailBean;
    }

    @Override
    public List<Map<String, Object>> getCategoryList() {
        //查取 pid 为0 的列表
        List<Map<String, Object>> categories =  categoryMapper.selectCategoryListByPid(0);
        //循环该列表，查询每一个的子列表   pid = id，并放在chriden这个 list 下
        for (Map category : categories) {
            int id = Integer.parseInt(category.get("id").toString());
            List<Map<String, Object>> children = categoryMapper.selectCategoryListByPid(id);
            category.put("children", children);
        }
        return categories;
    }

    @Override
    public List<Map> getSimpleCategoryList() {
        List<Map> categories =  categoryMapper.selectSimpleCategoryList();
        return categories;
    }

    @Override
    public Category insertCategory(Category category) {
        Date date = new Date();
        category.setAddTime(date);
        int result = categoryMapper.insertCategory(category);
        int id = category.getId();
        return categoryMapper.queryCategoryById(id);
    }

    @Override
    public void deleteCategory(CategoryBean categoryBean) {
        if (categoryBean.getChildren() != null){
            for (Category category : categoryBean.getChildren()){
                categoryMapper.deleteCategoryById(category.getId());
            }
        }
        categoryMapper.deleteCategoryById(categoryBean.getId());
    }

    @Override
    public void updateCategory(CategoryBean categoryBean) {
        Date date = new Date();
        categoryBean.setUpdateTime(date);
        categoryMapper.updateCategory(categoryBean);
    }

    @Override
    public ListBean<Issue> queryIssueList(int page, int limit, String sort, String order, String question) {
        String orderBy = String.format("%s %s",sort,order);
        PageHelper.startPage(page, limit, orderBy);
        List<Issue> issueList = issueMapper.queryIssueList(question);
        long total = issueMapper.queryIssueListTotal(question);
        ListBean<Issue> issueListBean = new ListBean<>(issueList, total);
        return issueListBean;
    }

    @Override
    public Issue insertIssueList(Issue issue) {
        Date date = new Date();
        issue.setAddTime(date);
        int result = issueMapper.insertIssueList(issue);
        int id = issue.getId();
        return issueMapper.queryIssueById(id);
    }

    @Override
    public Issue updateIssue(Issue issue) {
        Date date = new Date();
        issue.setUpdateTime(date);
        issueMapper.updateIssue(issue);
        return issueMapper.queryIssueById(issue.getId());
    }

    @Override
    public void deleteIssue(Integer id) {
        issueMapper.deleteIssueById(id);
    }

    @Override
    public ListBean<Keyword> queryKeyWordList(int page, int limit, String sort, String order, String keyword, String url) {
        String orderBy = String.format("%s %s",sort,order);
        PageHelper.startPage(page, limit, orderBy);
        List<Keyword> keywordList = keywordMapper.queryKeyWordList(keyword, url);
        long total = keywordMapper.queryKeyWordListTotal(keyword, url);
        ListBean<Keyword> keywordListBean = new ListBean<>(keywordList, total);
        return keywordListBean;
    }

    @Override
    public Keyword insertKeyWordList(Keyword keyword) {
        Date date = new Date();
        keyword.setAddTime(date);
        int result = keywordMapper.insertKeyWordList(keyword);
        int id = keyword.getId();
        return keywordMapper.queryKeyWordById(id);
    }

    @Override
    public Keyword updateKeyWord(Keyword keyword) {
        Date date = new Date();
        keyword.setUpdateTime(date);
        keywordMapper.updateKeyWord(keyword);
        return keywordMapper.queryKeyWordById(keyword.getId());
    }

    @Override
    public void deleteKeyWord(Integer id) {
        keywordMapper.deleteKeyWordById(id);
    }


}
