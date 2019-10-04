package com.cskaoyan.mall.vo;

/*国旭*/
public class CollectShow {
    private int page;
    private int limit;
    private String sort;
    private String order;
    private Object userId;
    private Object valueId;

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

    public Object getUserId() {
        return userId;
    }

    public void setUserId(Object userId) {
        this.userId = userId;
    }

    public Object getValueId() {
        return valueId;
    }

    public void setValueId(Object valueId) {
        this.valueId = valueId;
    }

    @Override
    public String toString() {
        return "CollectShow{" +
                "page=" + page +
                ", limit=" + limit +
                ", sort='" + sort + '\'' +
                ", order='" + order + '\'' +
                ", userId=" + userId +
                ", valueId=" + valueId +
                '}';
    }
}
