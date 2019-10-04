package com.cskaoyan.mall.controller.wechat;

import com.cskaoyan.mall.service.wechat.LRWXMallService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李锐
 */
@RestController
@RequestMapping("/wx")
public class LRWXMallController {

    @Autowired
    LRWXMallService lrwxMallService;

    @RequestMapping("/catalog/index")
    public BaseRespVo queryCatalogIndex() {
        return BaseRespVo.ok(lrwxMallService.queryCatalogIndex());
    }

    @RequestMapping("/goods/count")
    public BaseRespVo queryGoodsCount() {
        return BaseRespVo.ok(lrwxMallService.queryGoodsCount());
    }
}
