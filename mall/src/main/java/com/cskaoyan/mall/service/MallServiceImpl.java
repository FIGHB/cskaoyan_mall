package com.cskaoyan.mall.service;


import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.vo.MallBean.RegionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MallServiceImpl implements MallService {
    @Autowired
    RegionMapper regionMapper;

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
}
