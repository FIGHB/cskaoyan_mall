package com.cskaoyan.mall.vo.GuoVo;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Topic;

import java.util.List;

public class TopicDetail {
    private Topic topic;
    private String[] goods;

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public String[] getGoods() {
        return goods;
    }

    public void setGoods(String[] goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "TopicDetail{" +
                "topic=" + topic +
                ", goodsList=" + goods +
                '}';
    }
}
