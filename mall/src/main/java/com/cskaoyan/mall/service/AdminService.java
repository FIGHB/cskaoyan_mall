package com.cskaoyan.mall.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
public interface AdminService {
    Map<String, Object> getPageList(int page, int limit, String sort, String order) throws IOException;

    List<Map<String, Object>> getAllRoleOptions();

    Map<String, Object> getAllLogList(int page, int limit, String sort, String order);
}
