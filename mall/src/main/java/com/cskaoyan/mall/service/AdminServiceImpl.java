package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.selfmapper.MyAdminMapper;
import com.cskaoyan.mall.vo.List_AdminVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    SqlSession sqlSession;

    @Override
    public Map<String, Object> getPageList(int page, int limit, String sort, String order) throws IOException {
        sqlSession = sqlSessionFactory.openSession();
        MyAdminMapper adminMapper = sqlSession.getMapper(MyAdminMapper.class);
        List<Admin> admins = adminMapper.selectByExample(page, limit, sort, order);
        List<Object> objects = new ArrayList<>();
        for (Admin admin : admins) {
            List_AdminVo adminVo = new List_AdminVo();
            adminVo.setId(admin.getId());
            adminVo.setUsername(admin.getUsername());
            adminVo.setAvatar(admin.getAvatar());
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
}
