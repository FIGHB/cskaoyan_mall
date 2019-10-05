package com.cskaoyan.mall.vo.GuoVo;

import com.cskaoyan.mall.bean.Topic;

import java.util.List;

public class GuoTopicShow {
    List<Topic> data;
    Long count;

    public List<Topic> getData() {
        return data;
    }

    public void setData(List<Topic> data) {
        this.data = data;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "GuoTopicShow{" +
                "data=" + data +
                ", count=" + count +
                '}';
    }
}
