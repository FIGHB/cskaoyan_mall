package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.service.LiAuthService;
import com.cskaoyan.mall.service.LiLogService;
import com.cskaoyan.mall.shiro.CustomToken;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 和登录相关的 servlet
 * @author 李锐、周榆淮
 */
@RestController
public class AuthController {

    @Autowired
    LiAuthService authService;

    @Autowired
    LiLogService logService;

    @RequestMapping("admin/auth/login")
    public BaseRespVo login(@RequestBody LoginVo loginVo, HttpServletRequest request) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
//        String errmsg = authService.login(loginVo);
//        //如果不为空表明有错误需要修改;
        if(username == null || "".equals(username)) {
            return BaseRespVo.getBaseResVo(500,null, "用户名不能为空");
        } else if(password == null || "".equals(password)) {
            return BaseRespVo.getBaseResVo(500, null, "密码不能为空");
        }
        CustomToken token = new CustomToken(username, password, "admin");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            return BaseRespVo.getBaseResVo(500, null, "登陆失败");
        }
        Serializable id = subject.getSession().getId();
        //向日志中添加登录信息
        logService.addSucceedLog(request,username,1,"登录");
//        String ip = IpUtils.getIpAddr(request);
//        Log logMessage = new Log();
//        logMessage.setIp(ip);
//        logMessage.setAdmin(username);
//        logMessage.setType(1);
//        logMessage.setStatus(true);
//        logMessage.setAction("登录");
//        authService.addLog(logMessage);

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
    public BaseRespVo info() {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        UserInfo userInfo = authService.getAdminInfoByUserName(username);

        BaseRespVo ok = BaseRespVo.ok(userInfo);
        return ok;
    }

    @RequestMapping("admin/auth/logout")
    public BaseRespVo logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        logService.addSucceedLog(request,subject.getPrincipal().toString(),1,"登出");
        subject.logout();
        return BaseRespVo.ok("登出成功");
    }
}
