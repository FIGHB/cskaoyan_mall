package com.cskaoyan.mall.controller.ChenWuController;

import com.cskaoyan.mall.service.LiAuthService;
import com.cskaoyan.mall.shiro.CustomToken;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.ChenWuWx.WxLoginVo;
import com.cskaoyan.mall.vo.LoginVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
public class InAndOutController {

    @Autowired
    LiAuthService authService;

    @PostMapping("wx/auth/login")
    public BaseRespVo<WxLoginVo> login(@RequestBody LoginVo loginVo) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        Subject subject = SecurityUtils.getSubject();
        CustomToken token = new CustomToken(username, password,"wx");
        try {
            SecurityUtils.getSubject().login(token);
        } catch (AuthenticationException e) {
            return BaseRespVo.getBaseResVo(500, null, "登录失败");
        }
        Map wxLoginVo = authService.userLogin(username);
        wxLoginVo.put("token", subject.getSession().getId());
        return BaseRespVo.ok(wxLoginVo);
    }

    @PostMapping("wx/auth/logout")
    public BaseRespVo logOut(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        //将 token 从服务器端的缓存中删除
        subject.logout();
        return BaseRespVo.ok("登出成功");
    }

}
