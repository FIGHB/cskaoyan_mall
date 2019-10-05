package com.cskaoyan.mall.controller.wechat;

import com.cskaoyan.mall.service.wechat.LRWXMallService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/search/index")
    public BaseRespVo querySearchIndex() {
        Subject subject = SecurityUtils.getSubject();
        Object principal = subject.getPrincipal();
        String username = (String) principal;
        return BaseRespVo.ok(lrwxMallService.querySearchIndex(username));
    }

    @GetMapping("/search/helper")
    public BaseRespVo querySearchHelper(String keyword) {
        return BaseRespVo.ok(lrwxMallService.querySearchHelper(keyword));
    }

    @PostMapping("/search/clearhistory")
    public BaseRespVo deleteSearchHistory() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        boolean flag = lrwxMallService.deleteSearchHistory(username);
        if(flag) {
            return BaseRespVo.ok("成功");
        } else {
            return BaseRespVo.getBaseResVo(500, null, "删除失败");
        }
    }
}
