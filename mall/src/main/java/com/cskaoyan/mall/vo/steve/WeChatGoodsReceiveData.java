package com.cskaoyan.mall.vo.steve;

/**
 * @author Steve
 * @date 2019/10/5-15:36
 */
public class WeChatGoodsReceiveData {
    String keyword;
    Integer page;
    Integer size;
    String sort;
    String order;
    Integer categoryId;
    Integer brandId;
    boolean isHot;
    boolean isNew;

    public boolean getIsHot() {
        return isHot;
    }

    public void setIsHot(boolean hot) {
        isHot = hot;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(boolean aNew) {
        isNew = aNew;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
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

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }
}