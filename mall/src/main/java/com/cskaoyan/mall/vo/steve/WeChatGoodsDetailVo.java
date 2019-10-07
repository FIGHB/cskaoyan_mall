package com.cskaoyan.mall.vo.steve;

import com.cskaoyan.mall.bean.*;

import java.util.List;

/**
 * @author Steve
 * @date 2019/10/5-23:07
 */
public class WeChatGoodsDetailVo {
    List<GoodsAttribute> attribute;
    Brand brand;
    WeChatCommentVo comment;
    List<Groupon> groupon;
    Goods info;
    List<Issue> issue;
    List<GoodsProduct> productList;
    List<WeChatSpecVo> specificationList;
    //是否收藏
    Boolean userHasCollect;

    public WeChatGoodsDetailVo() {
    }

    public List<GoodsAttribute> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<GoodsAttribute> attribute) {
        this.attribute = attribute;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public WeChatCommentVo getComment() {
        return comment;
    }

    public void setComment(WeChatCommentVo comment) {
        this.comment = comment;
    }

    public List<Groupon> getGroupon() {
        return groupon;
    }

    public void setGroupon(List<Groupon> groupon) {
        this.groupon = groupon;
    }

    public Goods getInfo() {
        return info;
    }

    public void setInfo(Goods info) {
        this.info = info;
    }

    public List<Issue> getIssue() {
        return issue;
    }

    public void setIssue(List<Issue> issue) {
        this.issue = issue;
    }

    public List<GoodsProduct> getProductList() {
        return productList;
    }

    public void setProductList(List<GoodsProduct> productList) {
        this.productList = productList;
    }

    public List<WeChatSpecVo> getSpecificationList() {
        return specificationList;
    }

    public void setSpecificationList(List<WeChatSpecVo> specificationList) {
        this.specificationList = specificationList;
    }

    public Boolean getUserHasCollect() {
        return userHasCollect;
    }

    public void setUserHasCollect(Boolean userHasCollect) {
        this.userHasCollect = userHasCollect;
    }
}
