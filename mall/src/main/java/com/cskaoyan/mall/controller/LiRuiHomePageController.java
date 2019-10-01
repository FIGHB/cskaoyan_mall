package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.LiRuiHomepageService;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 首页 homepage
 * @author 李锐
 */
@RequestMapping("admin/")
@RestController
public class LiRuiHomePageController {

    @Autowired
    LiRuiHomepageService homepageService;

    @RequestMapping("dashboard")
    public BaseRespVo dashboard() {
        Map<String, Object> data = homepageService.getTotal();
        return BaseRespVo.ok(data);
    }


    //只有修改前后的密码，没有对象，暂时无法修改
    @RequestMapping("profile/password")
    public BaseRespVo changePassword(@RequestBody Map map) {
        String oldPassword = map.get("oldPassword").toString();
        String newPassword = map.get("newPassword").toString();
        String newPassword2 = map.get("newPassword2").toString();
        if(oldPassword == null || newPassword == null || newPassword2 == null) {
            return BaseRespVo.getBaseResVo(500, null, "抱歉密码不能为空");
        } else if (oldPassword.equals(newPassword) || oldPassword.equals(newPassword2)) {
            return BaseRespVo.getBaseResVo(500, null, "新旧密码不能一样");
        } else if (!newPassword.equals(newPassword2)) {
            return BaseRespVo.getBaseResVo(500, null, "两次密码不一致");
        }
        /*等待后续旧密码认证和密码修改*/
        System.out.println(map.toString());
        return BaseRespVo.ok(null);
    }
}
