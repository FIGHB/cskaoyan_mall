package com.cskaoyan.mall.vo;

import java.util.List;

public class GrouponListVO {
   private int count;
   private List<GrouponDataVO> data;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<GrouponDataVO> getData() {
        return data;
    }

    public void setData(List<GrouponDataVO> data) {
        this.data = data;
    }
}
