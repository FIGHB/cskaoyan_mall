package com.cskaoyan.mall.utils;

import java.util.List;

/**
 * 分页 所用
 * @author Steve
 * @date 2019/9/30-21:28
 */
public class SteveListBean<T> {
    private List<T> items;
    private long total;

    public SteveListBean(List<T> items, long total) {
        this.items = items;
        this.total = total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
