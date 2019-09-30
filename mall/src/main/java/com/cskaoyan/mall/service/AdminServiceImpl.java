package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.Storage;
import com.cskaoyan.mall.mapper.AdminMapper;
import com.cskaoyan.mall.mapper.selfmapper.MyAdminMapper;
import com.cskaoyan.mall.vo.List_AdminVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * @author 李锐
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    SqlSession sqlSession;

    @Override
    public Map<String, Object> getPageList(int page, int limit, String sort, String order, String username) throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        MyAdminMapper adminMapper = sqlSession.getMapper(MyAdminMapper.class);
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
        MyAdminMapper mapper = sqlSession.getMapper(MyAdminMapper.class);
        List<Map<String, Object>> roles = mapper.selectAllRole();
        return roles;
    }

    @Override
    public Map<String, Object> getAllLogList(int page, int limit, String sort, String order) {
        sqlSession = sqlSessionFactory.openSession();
        MyAdminMapper mapper = sqlSession.getMapper(MyAdminMapper.class);
        int offset = (page - 1) * limit;
        List<Log> list = mapper.getAllLogList(offset, limit, sort, order);
        HashMap<String, Object> map = new HashMap<>();
        map.put("items", list);
        int total = mapper.selectCountLog();
        map.put("total", total);
        return map;
    }

    @Override
    public Storage avatorUpload(File filePath, String fileName, long length, String requestURL) {
        sqlSession = sqlSessionFactory.openSession();
        MyAdminMapper mapper = sqlSession.getMapper(MyAdminMapper.class);
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
        MyAdminMapper mapper = sqlSession.getMapper(MyAdminMapper.class);
        int update = mapper.addAdmin(adminVo);
        if(update != 1) return false;
        int id = mapper.selectAdminId(adminVo);
        adminVo.setId(id);
        return true;
    }

    @Override
    public boolean updateAdmin(List_AdminVo adminVo) {
        sqlSession = sqlSessionFactory.openSession();
        MyAdminMapper mapper = sqlSession.getMapper(MyAdminMapper.class);
        int update = mapper.updateAdminByPrimaryKey(adminVo);
        if(update > 1 || update < 0) return false;
        Admin admin = mapper.selectAdminVoById(adminVo.getId());
        adminVo.setLastLoginIp(admin.getLastLoginIp());
        adminVo.setLastLoginTime(admin.getLastLoginTime());
        adminVo.setDeleted(admin.getDeleted());
        return true;
    }

    @Override
    public boolean deleteAdminById(Integer id) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        AdminMapper mapper = sqlSession.getMapper(AdminMapper.class);
        int update = mapper.deleteByPrimaryKey(id);
        return update > 0;
    }
}
