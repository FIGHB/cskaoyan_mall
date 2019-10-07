package com.cskaoyan.mall.shiro;

import com.cskaoyan.mall.mapper.selfmapper.MyUserMapper;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 李锐
 */
@Component
public class WxRealm extends AuthorizingRealm  {


    @Autowired
    MyUserMapper userMapper;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //subject执行login时传入的usernamePasswordToken中的username
        String username = (String) authenticationToken.getPrincipal();
        //第一个参数用户名，第二个参数为密码
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, userMapper.queryPasswordByUsername(username), this.getName());
        return authenticationInfo;
    }

    //并没有微信端的权限表，所以不加
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }


}
