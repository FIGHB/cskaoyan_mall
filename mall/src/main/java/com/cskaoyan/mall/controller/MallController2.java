package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.service.MallService2;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("admin")
public class MallController2 {

    @Autowired
    MallService2 mallService2;

    @RequestMapping("/category/list")
    public BaseRespVo getCategoryList() {
        List<Map<String, Object>> data = mallService2.getCategoryList();
        return BaseRespVo.ok(data);
    }

    @RequestMapping("/category/l1")
    public BaseRespVo getSimpaleCategoryList() {
        List<Category> data = mallService2.getSimpleCategoryList();
        return BaseRespVo.ok(data);
    }

}
