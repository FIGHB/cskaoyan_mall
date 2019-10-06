package com.cskaoyan.mall.controller.WXController;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.service.WXService.CollectService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectController {
    @Autowired
    CollectService collectService;

    private String getUsername(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        return username;
    }
    @RequestMapping("/wx/collect/list")
    public BaseRespVo list(int type,int page,int size){
        String username = getUsername();
        if(username==null){
            return BaseRespVo.fail(501,"请登录");
        }
        BaseRespVo baseRespVo = collectService.queryAllCollect(type, page, size,username);
        return baseRespVo;
    }

    @RequestMapping("/wx/collect/addordelete")
    public BaseRespVo addordelete(@RequestBody Collect collect){
        String username = getUsername();
        if(username==null){
            return BaseRespVo.fail(501,"请登录");
        }
        BaseRespVo addordelete = collectService.addordelete(collect,username);
        return addordelete;
    }

}
