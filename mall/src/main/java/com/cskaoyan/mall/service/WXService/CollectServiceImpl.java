package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.Collect;
import com.cskaoyan.mall.bean.CollectExample;
import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.wxbean.CollectList;
import com.cskaoyan.mall.mapper.CollectMapper;
import com.cskaoyan.mall.mapper.GoodsMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.mapper.WXMapper.WxfCollectMapper;
import com.cskaoyan.mall.mapper.WXMapper.WxfUserMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.CollectListVo;
import com.cskaoyan.mall.vo.Type;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectServiceImpl implements CollectService {
    @Autowired
    WxfCollectMapper wxfCollectMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    WxfUserMapper wxfUserMapper;
    @Override
    public BaseRespVo queryAllCollect(int type, int page, int size,String username) {
        //记得查询userId
        int userId = wxfUserMapper.queryUserId(username);
        PageHelper.startPage(page,size);
        List<CollectList> collectLists = wxfCollectMapper.queryAllCollect(type,userId);
        PageInfo<CollectList> goodsPageInfo = new PageInfo<>(collectLists);
        long total = goodsPageInfo.getTotal();
        return BaseRespVo.ok(new CollectListVo<>(collectLists,total));
    }

    @Override
    public BaseRespVo addordelete(Collect collect,String username) {
        //记得查询userId
        int userId = wxfUserMapper.queryUserId(username);
        collect.setUserId(userId);
        int flag = wxfCollectMapper.isCollect(collect);
        if(flag==0){
            Date date = new Date();
            collect.setAddTime(date);
            collect.setUpdateTime(date);
            collectMapper.insertSelective(collect);
            return BaseRespVo.ok(new Type("delete"));
        }else {
            wxfCollectMapper.delete(collect);
            return BaseRespVo.ok(new Type("add"));
        }

    }
}
