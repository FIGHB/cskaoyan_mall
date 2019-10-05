package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.vo.GuoVo.TopicDetail;

import java.util.List;

public interface TopicService {
    List<Topic> getTopicList();

    TopicDetail getTopicDetailById(Integer id);

    List<Region> getRegionList(Integer pid);
}
