package com.cskaoyan.mall.vo;

import java.util.List;
/*国旭*/
public class ItemsList<T> {
    private List<T> items;
    private long total;

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

    @Override
    public String toString() {
        return "ItemsList{" +
                "items=" + items +
                ", total=" + total +
                '}';
    }
}
