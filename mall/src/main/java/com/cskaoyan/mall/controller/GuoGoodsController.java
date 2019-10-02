package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class GuoGoodsController {
    @RequestMapping("/goods/list")
    public BaseRespVo getGoodsList(){
        return null;
    }
}
