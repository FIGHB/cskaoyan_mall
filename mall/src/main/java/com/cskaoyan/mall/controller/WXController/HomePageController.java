package com.cskaoyan.mall.controller.WXController;

import com.cskaoyan.mall.service.WXService.HomePageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx")
public class HomePageController {
    @Autowired
    HomePageService homePageService;

    @RequestMapping("/home/index")
    public BaseRespVo queryRegionList(){
        return BaseRespVo.ok(homePageService.queryHomePage());
    }

    @RequestMapping("/catalog/current")
    public BaseRespVo queryCurrentCategory(int id){
        return BaseRespVo.ok(homePageService.queryCurrentCategory(id));
    }

    @RequestMapping("/brand/list")
    public BaseRespVo queryBrandList(int page, int size){
        return BaseRespVo.ok(homePageService.queryBrandList(page,size));
    }

    @RequestMapping("/brand/detail")
    public BaseRespVo queryBrandDetail(int id){
        return BaseRespVo.ok(homePageService.queryBrandDetail(id));
    }

}
