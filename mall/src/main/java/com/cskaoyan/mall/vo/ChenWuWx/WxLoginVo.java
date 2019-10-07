package com.cskaoyan.mall.vo.ChenWuWx;



import java.io.Serializable;

public class WxLoginVo {
   String tokenExpire;
    Serializable token;
    UserInfo userInfo;

    public WxLoginVo() {
    }

    public WxLoginVo(String tokenExpire, String token, UserInfo userInfo) {
        this.tokenExpire = tokenExpire;
        this.token = token;
        this.userInfo = userInfo;
    }

    public String getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(String tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    public Serializable getToken() {
        return token;
    }

    public void setToken(Serializable token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
