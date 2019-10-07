package com.cskaoyan.mall.vo.ChenWuWx;

public class CWOrderListVo {
    Integer count;
    Integer totalPages;
    CWOrderStatus cwOrderStatus;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public CWOrderStatus getCwOrderStatus() {
        return cwOrderStatus;
    }

    public void setCwOrderStatus(CWOrderStatus cwOrderStatus) {
        this.cwOrderStatus = cwOrderStatus;
    }
}
