package com.cskaoyan.mall.controller.ChenWuController;

import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.LoginVo;

import com.cskaoyan.mall.vo.ChenWuWx.UserInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("wx/auth/")
public class WxController {
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


}
