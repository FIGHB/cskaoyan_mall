package com.cskaoyan.mall.vo;

public class DetailRespVo {
    private Object data;
    private long count;
    private int currentPage;

    public DetailRespVo() {
    }

    public DetailRespVo(Object data, long count, int currentPage) {
        this.data = data;
        this.count = count;
        this.currentPage = currentPage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
