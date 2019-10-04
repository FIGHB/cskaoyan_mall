package com.cskaoyan.mall.controller.WXController;

import com.cskaoyan.mall.service.WXService.HomePageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomePageController {
    @Autowired
    HomePageService homePageService;

    @RequestMapping("wx/home/index")
    public BaseRespVo queryRegionList(){
        return BaseRespVo.ok(homePageService.queryHomePage());
    }
}
