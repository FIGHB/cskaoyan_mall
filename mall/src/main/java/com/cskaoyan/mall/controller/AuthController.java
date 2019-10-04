package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.LiAuthService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 和登录相关的 servlet
 * @author 李锐、周榆淮
 */
@RestController
public class AuthController {

    @Autowired
    LiAuthService authService;

    @RequestMapping("admin/auth/login")
    public BaseRespVo login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        String errmsg = authService.login(loginVo);
        //如果不为空表明有错误需要修改;
        if(errmsg != null) {
            return BaseRespVo.getBaseResVo(500,null,errmsg);
        }
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return BaseRespVo.getBaseResVo(500, null, "登陆失败");
        }
        Serializable id = subject.getSession().getId();
        return BaseRespVo.ok(id);



/*        //去数据库查询是否存在，如果存在返回 null,如果出错返回 String 类型的错误信息
        String errmsg = authService.login(loginVo);
        //如果不为空表明有错误需要修改;
        if(errmsg != null) {
            return BaseRespVo.getBaseResVo(500,null,errmsg);
        }
        String ip = IpUtils.getIpAddr(request);
        //在ok方法里面,进行了操作,给errmsg,和errno 赋值啦
        return BaseRespVo.ok("8a0c90e9-4e3f-40d0-86b6-cb1b1ac01e9a");*/
    }

    @RequestMapping("admin/auth/info")
    public BaseRespVo info(String token) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        UserInfo userInfo = authService.getUserInfoByUserName(username);

        BaseRespVo ok = BaseRespVo.ok(userInfo);
        return ok;
    }

    @RequestMapping("admin/auth/logout")
    public BaseRespVo logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return BaseRespVo.ok("登出成功");
    }
}
