package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.mapper.BrandMapper;
import com.cskaoyan.mall.mapper.RegionMapper;
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
}
