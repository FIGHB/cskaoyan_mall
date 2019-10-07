package com.cskaoyan.mall.controller.ChenWuController;

import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.LoginVo;
import com.cskaoyan.mall.vo.ChenWuWx.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/auth/")
public class CHAuthController {
    @PostMapping("login")
    public BaseRespVo<LoginVo> login( String password, String username) {
        BaseRespVo<LoginVo> baseRespVo = new BaseRespVo<>();
        LoginVo loginVo = new LoginVo();
        UserInfo userInfo = new UserInfo();
        userInfo.setAvatarUrl("");
        userInfo.setNickname("wx");
        loginVo.setTokenExpire("2019-10-05T08:21:09");
        loginVo.setToken("I594fsb22qcd2q1ao7o6m6nbexjevpzm");
        loginVo.setUserInfo(userInfo);
        baseRespVo.setData(loginVo);
        return baseRespVo;
    }

    @PostMapping("login_by_weixin")
    public BaseRespVo login_By_Weixin(com.cskaoyan.mall.vo.UserInfo userInfo) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("错误");
        baseRespVo.setErrno(-1);
        return baseRespVo;
    }

    @PostMapping("logout")
    public BaseRespVo logOut() {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("请登录");
        baseRespVo.setErrno(501);
        return baseRespVo;
    }

    @PostMapping("register")
    public BaseRespVo register(String code,String mobile,String password,String username,String wxCode) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("验证码错误");
        baseRespVo.setErrno(703);
        return baseRespVo;
    }

    @PostMapping("reset")
    public BaseRespVo reset(String code,String mobile,String password) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("验证码错误");
        baseRespVo.setErrno(703);
        return baseRespVo;
    }

    @PostMapping("regCaptcha")
    public BaseRespVo regCaptcha(String mobile) {
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setErrmsg("小程序后台验证码服务不支持");
        baseRespVo.setErrno(701);
        return baseRespVo;
    }




}
