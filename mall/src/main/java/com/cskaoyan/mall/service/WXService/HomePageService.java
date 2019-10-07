package com.cskaoyan.mall.service.WXService;


import com.cskaoyan.mall.bean.wxfbean.HomePageVO;

import com.cskaoyan.mall.bean.wxfbean.BrandDetailBean;
import com.cskaoyan.mall.bean.wxfbean.BrandListBean;
import com.cskaoyan.mall.bean.wxfbean.CurrentCategoryBean;


public interface HomePageService {
     HomePageVO queryHomePage();

     CurrentCategoryBean queryCurrentCategory(int id);

     BrandListBean queryBrandList(int page, int size);

     BrandDetailBean queryBrandDetail(int id);
}
