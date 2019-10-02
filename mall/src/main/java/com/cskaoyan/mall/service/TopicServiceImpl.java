package com.cskaoyan.mall.service;


import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.mapper.selfmapper.WxfTopicMapper;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.PageVo;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements TopicService{
    @Autowired
    TopicMapper topicMapper;
    @Autowired
    WxfTopicMapper wxfTopicMapper;
    @Override
    public BaseRespVo selectAllTopic(int page,int limit,Topic topic) {
        PageHelper.startPage(page,limit);
        Topic[] topics = wxfTopicMapper.queryTopicByCondition(topic);
        long total = wxfTopicMapper.queryTotal(topic);
        return BaseRespVo.ok(new PageVo<>(topics, total));
    }

    @Override
    public BaseRespVo insert(Topic topic) {
        topicMapper.insertSelective(topic);
        Topic topicResp = wxfTopicMapper.selectLastInsert();
        return BaseRespVo.ok(topicResp);
    }

    @Override
    public BaseRespVo delete(Topic topic) {
        topicMapper.deleteByPrimaryKey(topic.getId());
        return BaseRespVo.ok(null);
    }

    @Override
    public BaseRespVo update(Topic topic) {
        topicMapper.updateByPrimaryKeySelective(topic);
        Topic topicResp = topicMapper.selectByPrimaryKey(topic.getId());
        return BaseRespVo.ok(topicResp);
    }
}
