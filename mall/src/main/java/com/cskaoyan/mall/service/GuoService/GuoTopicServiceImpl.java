package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.*;
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
    RegionMapper regionMapper;

    @Override
    public List<Topic> getTopicList() {
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Topic> topics = topicMapper.selectByExample(topicExample);
        return topics;
    }

    @Override
    public TopicDetail getTopicDetailById(Integer id) {
        TopicExample.Criteria criteria = topicExample.createCriteria();
        criteria.andIdEqualTo(id);
        List<Topic> topics = topicMapper.selectByExample(topicExample);

        Topic topic = topics.get(0);
        String[] goods = topic.getGoods();
        TopicDetail topicDetail = new TopicDetail();
        topicDetail.setTopic(topic);
        topicDetail.setGoods(goods);
        return topicDetail;
    }

    @Override
    public List<Region> getRegionList(Integer pid) {
        RegionExample regionExample = new RegionExample();
        RegionExample.Criteria criteriaRegion = regionExample.createCriteria();
        criteriaRegion.andPidEqualTo(pid);
        List<Region> regionList = regionMapper.selectByExample(regionExample);
        return regionList;
    }
}
