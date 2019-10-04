package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.mapper.LogMapper;
import com.cskaoyan.mall.mapper.selfmapper.LiAuthMapper;
import com.cskaoyan.mall.mapper.selfmapper.MyUserMapper;
import com.cskaoyan.mall.vo.LoginVo;
import com.cskaoyan.mall.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author 李锐
 */
@Service
public class LiAuthServiceImpl implements LiAuthService {


    @Autowired
    LiAuthMapper authMapper;

    @Autowired
    MyUserMapper userMapper;

    @Autowired
    LogMapper logMapper;

    /**
     * 如果用户名和密码正确则返回null，否则返回错误信息
     * @param loginVo
     * @return
     */
    @Override
    public String login(LoginVo loginVo) {
        String username = loginVo.getUsername();
        String password = loginVo.getPassword();
        if(username == null || username.equals("")) {
            return "用户名不能为空";
        }else if(password == null || password.equals("")) {
            return "密码不能为空";
        }
        int update = authMapper.login(loginVo);
        if(update == 0) {
            return "用户名或密码错误";
        }
        return null;
    }

    @Override
    public UserInfo getUserInfoByUserName(String username) {
//        Integer[] roleIds = userMapper.queryRoleIdsByUsername(username);
        Integer[] roleIds = userMapper.queryRoleIdVoByUsername(username).getRoleIds();
        UserInfo userInfo = userMapper.queryAdminInfoByUserName(username);
        ArrayList perms = new ArrayList();
        ArrayList roles = new ArrayList();
        for (Integer roleId : roleIds) {
            if(roleId == 1) {
                perms = new ArrayList();
                perms.add("*");
                userInfo.setPerms(perms);
                roles = new ArrayList();
                roles.add("超级管理员");
                userInfo.setRoles(roles);
                return userInfo;
            }
        }
        for (Integer roleId : roleIds) {
            roles.add(authMapper.getNameById(roleId));
            perms.addAll(authMapper.getPermissionsByRoleIds(roleId));
        }
/******************************************************************************/
        ArrayList<String> strings = changePermession(perms);

/******************************************************************************/
        userInfo.setPerms(strings);
        userInfo.setRoles(roles);
        return userInfo;
    }

    private ArrayList<String> changePermession(ArrayList perms) {
        ArrayList<String> lists = new ArrayList<>();
        for (Object perm : perms) {
            String perm2 = new String(perm.toString());
            String perm3 = "% /" + perm2.replace(":", "/") + "%";
            String perm4 = authMapper.getPermissionApiByPerm(perm3);
            if(perm4 == null || "".equals(perm4)) {
                String perm5 = "/" + perm2.replace(":", "/");
                lists.add("POST " + perm5);
                lists.add("GET " + perm5);
            } else {
                lists.add(perm4);
            }
            System.out.println(perm);
        }
        return lists;
    }

    @Override
    public void addLog(Log logMessage) {
//        Date date = new Date();
//        logMessage.setAddTime(date);
//        logMessage.setUpdateTime(date);
//        logMessage.setDeleted(false);
//        int update = logMapper.insert(logMessage);
    }
}
