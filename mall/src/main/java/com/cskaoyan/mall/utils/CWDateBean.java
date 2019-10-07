package com.cskaoyan.mall.utils;

import com.cskaoyan.mall.bean.System;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CWDateBean {
    //改变当前的时间格式
    public static String getCurrentTime(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = simpleDateFormat.format(date);
        return format;
    }
}
