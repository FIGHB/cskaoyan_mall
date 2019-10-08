package com.cskaoyan.mall.vo;

import java.util.List;

public class MyGrouponVO {
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<MyGrouponDataVO> getData() {
        return data;
    }

    public void setData(List<MyGrouponDataVO> data) {
        this.data = data;
    }

    List<MyGrouponDataVO> data;
}
