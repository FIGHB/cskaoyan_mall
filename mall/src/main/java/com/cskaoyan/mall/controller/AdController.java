package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.service.AdService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * author:王小凤
 */
@RestController
public class AdController {
    @Autowired
    AdService adService;

    @RequestMapping("/admin/ad/list")
    public BaseRespVo adList(int page, int limit,Ad ad){
        BaseRespVo ads = null;
        if(ad.getName()==null){
            ads = adService.queryAllAd(page, limit);
        }else {
            ads = adService.queryByNameAndContent(page, limit,ad);
        }
        return ads;
    }

    @RequestMapping("/admin/ad/update")
    public BaseRespVo update(@RequestBody Ad ad){
        BaseRespVo update = adService.update(ad);
        return update;
    }

    @RequestMapping("/admin/ad/create")
    public BaseRespVo create(@RequestBody Ad ad){
        BaseRespVo insert = adService.insert(ad);
        return insert;
    }

    @RequestMapping("/admin/ad/delete")
    public BaseRespVo delete(@RequestBody Ad ad){
        BaseRespVo delete = adService.delete(ad);
        return delete;
    }


}
