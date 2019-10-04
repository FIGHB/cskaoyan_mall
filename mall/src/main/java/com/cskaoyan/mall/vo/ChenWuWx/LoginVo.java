package com.cskaoyan.mall.vo.ChenWuWx;



import java.util.Date;

public class LoginVo {
   String tokenExpire;
    String token;
    UserInfo userInfo;

    public LoginVo() {
    }

    public LoginVo(String tokenExpire, String token, UserInfo userInfo) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
