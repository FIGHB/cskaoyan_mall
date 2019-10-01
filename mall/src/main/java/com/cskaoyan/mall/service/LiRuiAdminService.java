package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.vo.List_AdminVo;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
public interface LiRuiAdminService {
    Map<String, Object> getPageList(int page, int limit, String sort, String order, String username) throws IOException;

    List<Map<String, Object>> getAllRoleOptions();

    Map<String, Object> getAllLogList(int page, int limit, String sort, String order, String name);

    Storage avatorUpload(File filePath, String fileName, long length, String requestURL);

    boolean addAdmin(List_AdminVo adminVo);

    boolean addRole(Role role);

    boolean updateAdmin(List_AdminVo adminVo);

    boolean updateRole(Role role);

    boolean deleteAdminById(Integer id);

    boolean deleteRoleById(Integer id);

    Map<String, Object> getRoleList(int page, int limit, String sort, String order, String name);

    //对象存储部分
    //获取 storage 对象的列表
    Map<String, Object> getStorageList(int page, int limit, String sort, String order, String key, String name);

    boolean updateStorage(Storage storage);

    boolean deleteStorageById(Integer id);

    Map<String, Object> getPermissionList(String roleId);

    boolean addPermissions(int roleId, List<String> permissions);
}
