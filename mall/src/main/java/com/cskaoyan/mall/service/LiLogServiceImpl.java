package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.mapper.LogMapper;
import com.cskaoyan.mall.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service
public class LiLogServiceImpl implements LiLogService{

    @Autowired
    LogMapper logMapper;

    public boolean addSucceedLog(HttpServletRequest request, String admin, int type, String action) {
        return addLog(request, admin, type, action, true);
    }

    public boolean addFalseLog(HttpServletRequest request, String admin, int type, String action) {
        return addLog(request, admin, type, action,false);
    }

    public boolean addLog(HttpServletRequest request, String admin, int type, String action, boolean status) {
        Log logMessage = new Log();
        //admin
        logMessage.setAdmin(admin);
        //ip
        if (request != null) {
            String ipAddr = IpUtils.getIpAddr(request);
            logMessage.setIp(ipAddr);
        }
        //type
        /*
         * 0: '一般操作' 查询
         * 1: '安全操作' 登录、登出
         * 2: '订单操作' 前台相关
         * 3: '其他操作'
         * */
        logMessage.setType(type);
        //action
        logMessage.setAction(action);
        //status
        logMessage.setStatus(status);
        //time
        Date date = new Date();
        logMessage.setAddTime(date);
        logMessage.setUpdateTime(date);
        //deleted
        logMessage.setDeleted(false);
        int update = logMapper.insert(logMessage);
        return update > 0;
    }
}
