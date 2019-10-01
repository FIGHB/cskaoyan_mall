package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.mapper.selfmapper.MallMapper2;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MallServiceImpl2 implements MallService2 {

    @Autowired
    MallMapper2 mallMapper2;

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    SqlSession sqlSession;

    @Override
    public List<Map<String, Object>> getCategoryList() {
        //查取 pid 为0 的列表
        List<Map<String, Object>> categories =  mallMapper2.selectCategoryListByPid(0);
        //循环该列表，查询每一个的子列表   pid = id，并放在chriden这个 list 下
        for (Map category : categories) {
            int id = Integer.parseInt(category.get("id").toString());
            List<Map<String, Object>> children =  mallMapper2.selectCategoryListByPid(id);
            category.put("children", children);
        }
        return categories;
    }

    @Override
    public List<Category> getSimpleCategoryList() {
        List<Category> categories =  mallMapper2.selectSimpleCategoryList();
        return categories;
    }
}
