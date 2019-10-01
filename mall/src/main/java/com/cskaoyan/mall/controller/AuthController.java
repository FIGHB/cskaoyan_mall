package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class AuthController {

    @RequestMapping("admin/auth/login")
    public BaseRespVo login(@RequestBody LoginVo loginVo) {
        BaseRespVo<Object> objectBaseRespVo = new BaseRespVo<>();
//        objectBaseRespVo.setData("8a0c90e9-4e3f-40d0-86b6-cb1b1ac01e9a");
//        objectBaseRespVo.setErrmsg("成功");
//        objectBaseRespVo.setErrno(0);
//        return objectBaseRespVo;

        //在ok方法里面,进行了操作,给errmsg,和errno 赋值啦
        BaseRespVo baseRespVo = BaseRespVo.ok("8a0c90e9-4e3f-40d0-86b6-cb1b1ac01e9a");
        return baseRespVo;
    }

    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token) {

        UserInfo userInfo = new UserInfo();
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userInfo.setName("admin123");

        ArrayList<Object> perms = new ArrayList<>();
        perms.add("*");
        userInfo.setPerms(perms);

        ArrayList<Object> roles = new ArrayList<>();
        roles.add("超级管理员");
        userInfo.setRoles(roles);

        BaseRespVo ok = BaseRespVo.ok(userInfo);
        return ok;
    }
}
