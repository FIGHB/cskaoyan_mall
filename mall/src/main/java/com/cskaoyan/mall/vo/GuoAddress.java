package com.cskaoyan.mall.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class GuoAddress {

    private Boolean deleted;

    private String area;

    private String province;

    private String city;

    private Boolean isDefault;

    private Integer areaId;

    private String address;

    private String name;

    private String mobile;

    private Integer id;

    private Integer cityId;

    private Integer provinceId;

    private Integer userId;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public Integer getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "GuoAddress{" +
                "deleted=" + deleted +
                ", area='" + area + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", isDefault=" + isDefault +
                ", areaId=" + areaId +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", id=" + id +
                ", cityId=" + cityId +
                ", provinceId=" + provinceId +
                '}';
    }
}
