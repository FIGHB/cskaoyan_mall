package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.Brand;
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
}
