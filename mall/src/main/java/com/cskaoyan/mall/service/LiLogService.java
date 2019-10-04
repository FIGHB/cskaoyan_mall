package com.cskaoyan.mall.service;

import javax.servlet.http.HttpServletRequest;

public interface LiLogService {
    public boolean addSucceedLog(HttpServletRequest request, String admin, int type, String action);

    public boolean addFalseLog(HttpServletRequest request, String admin, int type, String action);

    public boolean addLog(HttpServletRequest request, String admin, int type, String action, boolean status);


    }
