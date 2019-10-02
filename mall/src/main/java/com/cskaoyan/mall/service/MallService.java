package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.utils.ListBean;
import com.cskaoyan.mall.vo.MallBean.RegionBean;

import java.math.BigDecimal;
import java.util.List;

public interface MallService {
     List<RegionBean> queryRegionList();

     ListBean<Brand> queryBrandList(int page,int limit,String sort,String order,Integer id,String name);

     Brand insertBrandList(Brand brand);

     Brand updateBrand(Brand brand);

     void deleteBrand(Integer id);

     ListBean<Order> queryOrderList(int page, int limit, String sort, String order, Integer userId, String orderSn, Short[] orderStatusArray);

     ListBean<Issue> queryIssueList(int page, int limit, String sort, String order, String question);

     Issue insertIssueList(Issue issue);

     Issue updateIssue(Issue issue);

     void deleteIssue(Integer id);

     ListBean<Keyword> queryKeyWordList(int page, int limit, String sort, String order, String keyword, String url);

     Keyword insertKeyWordList(Keyword keyword);

     Keyword updateKeyWord(Keyword keyword);

     void deleteKeyWord(Integer id);
}
