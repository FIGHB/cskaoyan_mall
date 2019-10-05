package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.GuoMapper.GuoWXRegionMapper;
import com.cskaoyan.mall.mapper.GuoMapper.GuoWXTopicMapper;
import com.cskaoyan.mall.mapper.RegionMapper;
import com.cskaoyan.mall.mapper.TopicMapper;
import com.cskaoyan.mall.vo.GuoVo.TopicDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuoTopicServiceImpl implements GuoTopicService {

    TopicExample topicExample = new TopicExample();

    @Autowired
    TopicMapper topicMapper;
    @Autowired
    GuoWXRegionMapper guoWXRegionMapper;
    @Autowired
    GuoWXTopicMapper guoWXTopicMapper;

    @Override
    public List<Topic> getTopicList() {
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        return topics;
    }

    @Override
    public TopicDetail getTopicDetailById(Integer id) {
        Topic topic = guoWXTopicMapper.getTopicById(id);

        String[] goods = topic.getGoods();
        TopicDetail topicDetail = new TopicDetail();
        topicDetail.setTopic(topic);
        topicDetail.setGoods(goods);
        return topicDetail;
    }

    @Override
    public List<Region> getRegionList(Integer pid) {
        List<Region> regionList = guoWXRegionMapper.getRegionList(pid);
        return regionList;
    }
}
