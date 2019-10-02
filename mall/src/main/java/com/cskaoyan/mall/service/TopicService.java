package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.vo.BaseRespVo;

public interface TopicService {

    BaseRespVo selectAllTopic(int page,int limit,Topic topic);

    BaseRespVo insert(Topic topic);

    BaseRespVo delete(Topic topic);

    BaseRespVo update(Topic topic);
}
