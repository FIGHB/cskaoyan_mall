package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.RoleMapper;
import com.cskaoyan.mall.mapper.StorageMapper;
import com.cskaoyan.mall.mapper.selfmapper.LiRuiAdminMapper;
import com.cskaoyan.mall.vo.List_AdminVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
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
    SqlSessionFactory sqlSessionFactory;

    SqlSession sqlSession;

    @Override
    public Map<String, Object> getPageList(int page, int limit, String sort, String order, String username) throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper adminMapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        List<Admin> admins;
        int offset = (page - 1) * limit;
        if(username == null) {
            admins = adminMapper.selectByExample(offset, limit, sort, order);
        } else {
            admins = adminMapper.selectByUsername(username, offset, limit, sort, order);
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
        int total = adminMapper.selectCountAdmin();
        map.put("total", total);
        return map;
    }


    @Override
    public List<Map<String, Object>> getAllRoleOptions() {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        List<Map<String, Object>> roles = mapper.selectAllRole();
        return roles;
    }

    @Override
    public Map<String, Object> getAllLogList(int page, int limit, String sort, String order, String name) {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        int offset = (page - 1) * limit;
        List<Log> list = mapper.getAllLogList(offset, limit, sort, order, name);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", list);
        int total = mapper.selectCountLog();
        map.put("total", total);
        return map;
    }

    @Override
    public Storage avatorUpload(File filePath, String fileName, long length, String requestURL) {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
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
        int update = mapper.insertStorge(storage);
        if(update > 0) {
            int id = mapper.selectStorgeId(storage);
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
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        int update = mapper.addAdmin(adminVo);
        if(update != 1) return false;
        int id = mapper.selectAdminId(adminVo);
        adminVo.setId(id);
        return true;
    }

    @Override
    public boolean addRole(Role role) {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        if(role == null) return false;
        Date date = new Date();
        role.setAddTime(date);
        role.setUpdateTime(date);
        int update = mapper.addRole(role);
        if(update != 1) return false;
        int id = mapper.selectRoleId(role);
        role.setId(id);
        return true;
    }

    @Override
    public boolean updateAdmin(List_AdminVo adminVo) {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        int update = mapper.updateAdminByPrimaryKey(adminVo);
        if(update > 1 || update < 0) return false;
        Admin admin = mapper.selectAdminVoById(adminVo.getId());
        adminVo.setLastLoginIp(admin.getLastLoginIp());
        adminVo.setLastLoginTime(admin.getLastLoginTime());
        adminVo.setDeleted(admin.getDeleted());
        return true;
    }

    @Override
    public boolean updateRole(Role role) {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        int update = mapper.updateRoleByPrimaryKey(role);
        if(update != 1) return false;
        role = mapper.selectRoleById(role.getId());
        return true;
    }

    @Override
    public boolean deleteAdminById(Integer id) {
        sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int update = mapper.deleteByPrimaryKey(id);
        return update > 0;
    }

    @Override
    public boolean deleteRoleById(Integer id) {
        sqlSession = sqlSessionFactory.openSession();
        RoleMapper mapper = sqlSession.getMapper(RoleMapper.class);
        int update = mapper.deleteByPrimaryKey(id);
        return update > 0;
    }

    @Override
    public Map<String, Object> getRoleList(int page, int limit, String sort, String order, String name) {
        sqlSession = sqlSessionFactory.openSession();
        LiRuiAdminMapper mapper = sqlSession.getMapper(LiRuiAdminMapper.class);
        int offset = (page - 1) * limit;
        List<Role> roles = mapper.selectRoleList(offset, limit, sort, order, name);
        int count = mapper.selectCountRole();
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", new Long(count).intValue());
        map.put("items", roles);
        return map;
    }

    @Override
    public boolean addPermissions(int roleId, List<String> permissions) {
        return false;
    }

    @Override
    public Map<String, Object> getPermissionList(String roleId) {

        /*中间核心部分*/
        String[] assignedPermissions = myAdminMapper.selectPermissionByRoleId(roleId);

        HashMap<String, Object> map = new HashMap<>();
        map.put("systemPermissions", "");
        map.put("assignedPermissions", assignedPermissions);
        return map;
    }

    //对象存储

    @Autowired
    LiRuiAdminMapper myAdminMapper;

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
        sqlSession = sqlSessionFactory.openSession();
        StorageMapper mapper = sqlSession.getMapper(StorageMapper.class);
        int update = mapper.updateByPrimaryKey(storage);
        return update >= 0;
    }

    @Override
    public boolean deleteStorageById(Integer id) {
        sqlSession = sqlSessionFactory.openSession();
        StorageMapper mapper = sqlSession.getMapper(StorageMapper.class);
        int update = mapper.deleteByPrimaryKey(id);
        return update > 0;
    }
}
