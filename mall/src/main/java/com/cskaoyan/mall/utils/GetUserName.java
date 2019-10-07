package com.cskaoyan.mall.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class GetUserName {
    public static String getUserName(){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        return username;
    }
}
