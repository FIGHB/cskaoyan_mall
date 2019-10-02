package com.cskaoyan.mall.vo;

public class PageVo <T> {
    private T[] items;
    private long total;

    public PageVo() {
    }

    public PageVo(T[] items, long total) {
        this.items = items;
        this.total = total;
    }

    public Object[] getItems() {
        return items;
    }

    public void setItems(T[] items) {
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
