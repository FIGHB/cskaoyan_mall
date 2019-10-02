package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.utils.ListBean;
import com.cskaoyan.mall.vo.MallBean.RegionBean;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
        int result = brandMapper.insertBrandList(brand);
        int id = brand.getId();
        return brandMapper.queryBrandById(id);
    }

    @Override
    public Brand updateBrand(Brand brand) {
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
        int result = issueMapper.insertIssueList(issue);
        int id = issue.getId();
        return issueMapper.queryIssueById(id);
    }

    @Override
    public Issue updateIssue(Issue issue) {
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
        int result = keywordMapper.insertKeyWordList(keyword);
        int id = keyword.getId();
        return keywordMapper.queryKeyWordById(id);
    }

    @Override
    public Keyword updateKeyWord(Keyword keyword) {
        keywordMapper.updateKeyWord(keyword);
        return keywordMapper.queryKeyWordById(keyword.getId());
    }

    @Override
    public void deleteKeyWord(Integer id) {
        keywordMapper.deleteKeyWordById(id);
    }
}
