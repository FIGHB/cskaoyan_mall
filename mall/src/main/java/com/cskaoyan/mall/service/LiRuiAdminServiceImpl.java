package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.mapper.selfmapper.LiRuiAdminMapper;
import com.cskaoyan.mall.utils.SystemPermissionUtils;
import com.cskaoyan.mall.vo.List_AdminVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.System;
import java.util.*;

/**
 * @author 李锐
 */
@Service
public class LiRuiAdminServiceImpl implements LiRuiAdminService {

    @Autowired
    LiRuiAdminMapper myAdminMapper;

    @Autowired
    AdminMapper adminMapper;

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    StorageMapper storageMapper;


    @Override
    public Map<String, Object> getPageList(int page, int limit, String sort, String order, String username) throws IOException {
        List<Admin> admins;
        int offset = (page - 1) * limit;
        if(username == null) {
            admins = myAdminMapper.selectByExample(offset, limit, sort, order);
        } else {
            admins = myAdminMapper.selectByUsername(username, offset, limit, sort, order);
        }
        List<Object> objects = new ArrayList<>();
        for (Admin admin : admins) {
            List_AdminVo adminVo = new List_AdminVo();
            adminVo.setId(admin.getId());
            adminVo.setUsername(admin.getUsername());
            adminVo.setAvatar(admin.getAvatar());
            if(username != null) {
                adminVo.setUsername(admin.getUsername());
            }
            String roleIds = admin.getRoleIds();
//            roleIds = roleIds.replace("[", "");
//            roleIds = roleIds.replace("]", "");
//            String[] split = roleIds.split(",");

            ObjectMapper objectMapper = new ObjectMapper();
            Integer[] integers = objectMapper.readValue(roleIds, Integer[].class);
            adminVo.setRoleIds(integers);
            objects.add(adminVo);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("items", objects);
        int total = myAdminMapper.selectCountAdmin();
        map.put("total", total);
        return map;
    }


    @Override
    public List<Map<String, Object>> getAllRoleOptions() {
        List<Map<String, Object>> roles = myAdminMapper.selectAllRole();
        return roles;
    }

    @Override
    public Map<String, Object> getAllLogList(int page, int limit, String sort, String order, String name) {
        int offset = (page - 1) * limit;
        List<Log> list = myAdminMapper.getAllLogList(offset, limit, sort, order, name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", list);
        int total = myAdminMapper.selectCountLog();
        map.put("total", total);
        return map;
    }

    @Override
    public Storage avatorUpload(File filePath, String fileName, long length, String requestURL) {
        Storage storage = new Storage();
        storage.setKey(fileName);
        storage.setName(fileName);
        storage.setType("image/jpeg");
        int size = new Long(length).intValue();
        storage.setSize(size);
        System.out.println(requestURL);
        storage.setUrl(requestURL);
//        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
//        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();// 获取当前时间
        storage.setAddTime(date);
        storage.setUpdateTime(date);
        storage.setDeleted(false);
        int update = myAdminMapper.insertStorge(storage);
        if(update > 0) {
            int id = myAdminMapper.selectStorgeId(storage);
            storage.setId(id);
        }
        return storage;
    }

    @Override
    public boolean addAdmin(List_AdminVo adminVo) {
        if(adminVo == null) {
            return false;
        }
        adminVo.setDeleted(false);
        Date date = new Date();// 获取当前时间
        adminVo.setAddTime(date);
        adminVo.setUpdateTime(date);
        adminVo.setLastLoginIp(null);
        adminVo.setLastLoginTime(date);
        int update = myAdminMapper.addAdmin(adminVo);
        if(update != 1) return false;
        int id = myAdminMapper.selectAdminId(adminVo);
        adminVo.setId(id);
        return true;
    }

    @Override
    public boolean addRole(Role role) {
        if(role == null) return false;
        Date date = new Date();
        role.setAddTime(date);
        role.setUpdateTime(date);
        int update = myAdminMapper.addRole(role);
        if(update != 1) return false;
        int id = myAdminMapper.selectRoleId(role);
        role.setId(id);
        return true;
    }

    @Override
    public boolean updateAdmin(List_AdminVo adminVo) {
        int update = myAdminMapper.updateAdminByPrimaryKey(adminVo);
        if(update > 1 || update < 0) return false;
        Admin admin = myAdminMapper.selectAdminVoById(adminVo.getId());
        adminVo.setLastLoginIp(admin.getLastLoginIp());
        adminVo.setLastLoginTime(admin.getLastLoginTime());
        adminVo.setDeleted(admin.getDeleted());
        return true;
    }

    @Override
    public boolean updateRole(Role role) {
        int update = myAdminMapper.updateRoleByPrimaryKey(role);
        if(update != 1) return false;
        role = myAdminMapper.selectRoleById(role.getId());
        return true;
    }

    @Override
    public boolean deleteAdminById(Integer id) {
        int update = adminMapper.deleteByPrimaryKey(id);
        return update > 0;
    }

    @Override
    public boolean deleteRoleById(Integer id) {
        int update = roleMapper.deleteByPrimaryKey(id);
        return update > 0;
    }

    @Override
    public Map<String, Object> getRoleList(int page, int limit, String sort, String order, String name) {
        int offset = (page - 1) * limit;
        List<Role> roles = myAdminMapper.selectRoleList(offset, limit, sort, order, name);
        int count = myAdminMapper.selectCountRole();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", new Long(count).intValue());
        map.put("items", roles);
        return map;
    }

    @Override
    public boolean addPermissions(int roleId, List<String> permissions) {
        int count = 0;
        for (String permission : permissions) {
            int update = myAdminMapper.selectPermissionByPermission(roleId, permission);
            if(update >= 1)count += myAdminMapper.addPermission(roleId, permission);
        }
        return count >= 0;
    }

    @Override
    public Map<String, Object> getPermissionList(String roleId) {

        /*中间核心部分*/
        List<String> assignedPermissions = myAdminMapper.selectPermissionByRoleId(roleId);
        //systemPermissions
        List<Map<String, Object>> lists = SystemPermissionUtils.getSysPermission(assignedPermissions);

        HashMap<String, Object> map = new HashMap<>();
        map.put("systemPermissions", lists);
        map.put("assignedPermissions", assignedPermissions);
        return map;
    }

    //对象存储



    @Override
    public Map<String, Object> getStorageList(int page, int limit, String sort, String order, String key, String name) {
        int offset = (page - 1) * limit;
        List<Storage> storages = myAdminMapper.getStorageList(offset,limit,sort,order,key,name);
        int count = myAdminMapper.selectCountStorage();
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", storages);
        map.put("total", count);
        return map;
    }

    @Override
    public boolean updateStorage(Storage storage) {
        int update = storageMapper.updateByPrimaryKey(storage);
        return update >= 0;
    }

    @Override
    public boolean deleteStorageById(Integer id) {
        int update = storageMapper.deleteByPrimaryKey(id);
        return update > 0;
    }
}
