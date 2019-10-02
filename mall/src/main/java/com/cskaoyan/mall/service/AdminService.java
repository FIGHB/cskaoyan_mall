package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.vo.List_AdminVo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
public interface AdminService {
    Map<String, Object> getPageList(int page, int limit, String sort, String order, String username) throws IOException;

    List<Map<String, Object>> getAllRoleOptions();

    Map<String, Object> getAllLogList(int page, int limit, String sort, String order);

    Storage avatorUpload(File filePath, String fileName, long length, String requestURL);

    boolean addAdmin(List_AdminVo adminVo);

    boolean updateAdmin(List_AdminVo adminVo);

    boolean deleteAdminById(Integer id);
}
