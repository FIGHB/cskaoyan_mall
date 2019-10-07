package com.cskaoyan.mall.vo;

import java.util.List;

public class CollectListVo<T> {
    private List<T> collectList;
    private long total;

    public CollectListVo() {
    }

    public CollectListVo(List<T> collectList, long total) {
        this.collectList = collectList;
        this.total = total;
    }

    public List<T> getCollectList() {
        return collectList;
    }

    public void setCollectList(List<T> collectList) {
        this.collectList = collectList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
