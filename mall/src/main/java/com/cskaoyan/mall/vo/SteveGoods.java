package com.cskaoyan.mall.vo;

/**
 * @author Steve
 * @date 2019/9/30-22:36
 */
public class SteveGoods {
    int page;
    int limit;
    String goodsSn;
    String name;
    String add_time;
    String order;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }


    public String getName() {
        return name;

    }

    public String getGoodsSn() {
        return goodsSn;
    }

    public void setGoodsSn(String goodsSn) {
        this.goodsSn = goodsSn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdd_time() {
        return add_time;
    }

    public void setAdd_time(String add_time) {
        this.add_time = add_time;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}