package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.wxfbean.BrandDetailBean;
import com.cskaoyan.mall.bean.wxfbean.BrandListBean;
import com.cskaoyan.mall.bean.wxfbean.CurrentCategoryBean;
import com.cskaoyan.mall.bean.wxfbean.HomePageVO;

public interface HomePageService {
     HomePageVO queryHomePage();

     CurrentCategoryBean queryCurrentCategory(int id);

     BrandListBean queryBrandList(int page, int size);

     BrandDetailBean queryBrandDetail(int id);
}
