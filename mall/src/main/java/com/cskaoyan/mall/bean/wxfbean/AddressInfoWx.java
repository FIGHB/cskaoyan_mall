package com.cskaoyan.mall.bean.wxfbean;

import java.util.Date;

public class AddressInfoWx {
    int id;
    String address;
    int areaId;
    int cityId;
    int provinceId;
    boolean isDefault;
    String mobile;
    String name;

    @Override
    public String toString() {
        return "AddressInfoWx{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", areaId=" + areaId +
                ", cityId=" + cityId +
                ", provinceId=" + provinceId +
                ", isDefault=" + isDefault +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }


    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
