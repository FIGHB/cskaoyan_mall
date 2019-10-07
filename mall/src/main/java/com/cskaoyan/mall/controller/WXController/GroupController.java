package com.cskaoyan.mall.controller.WXController;
import com.cskaoyan.mall.service.WXService.GrouponListService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/groupon")
public class GroupController {
    @Autowired
    GrouponListService grouponService;

    @RequestMapping("/list")
    public BaseRespVo queryGrouponList(int page, int size){
        return BaseRespVo.ok(grouponService.queryGrouponList(page,size));
    }

    @RequestMapping("/my")
    public BaseRespVo queryMyGroupon(int showType){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        return BaseRespVo.ok(grouponService.queryMyGroupOn(showType,username));
    }


}
