package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;

import java.util.Map;

/**
 * 和登录认证相关的service层接口
 * @author 李锐
 */
public interface LiAuthService {

    String login(LoginVo loginVo);

    UserInfo getAdminInfoByUserName(String username);

    void addLog(Log logMessage);

    Map userLogin(String username);
}
