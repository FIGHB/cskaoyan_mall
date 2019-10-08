package com.cskaoyan.mall.utils;

import com.cskaoyan.mall.bean.StringTopic;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.vo.BaseRespObject;
import com.cskaoyan.mall.vo.BaseRespVo;

import java.math.BigDecimal;
import java.util.HashMap;

public class SetTopic {

    public static Topic setTopic(Topic topic, StringTopic stringTopic){

        topic.setTitle(stringTopic.getTitle());
        topic.setSubtitle(stringTopic.getSubtitle());
        topic.setContent(stringTopic.getContent());
        topic.setGoods(stringTopic.getGoods());
        topic.setPicUrl(stringTopic.getPicUrl());
        topic.setReadCount(stringTopic.getReadCount());

        return topic;
    }

}
