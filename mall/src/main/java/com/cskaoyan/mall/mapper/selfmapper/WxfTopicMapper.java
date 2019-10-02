package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.Topic;

public interface WxfTopicMapper {

    Topic [] selectAllTopic();

    long queryTotal(Topic topic);

    Topic selectLastInsert();

    Topic [] queryTopicByCondition(Topic topic);
}
