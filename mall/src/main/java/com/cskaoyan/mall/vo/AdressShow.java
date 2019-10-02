package com.cskaoyan.mall.vo;

/*国旭*/
public class AdressShow {
    private int page;
    private int limit;
    private String sort;
    private String order;
    private Integer userId;
    private String name;

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AdressShow{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", id=" + userId +
                ", name='" + name + '\'' +
                '}';
    }
}
