package com.cskaoyan.mall.bean.wxfbean;

import java.util.Date;

public class AddressDetailWx {
    int id;
    String address;
    int areaId;
    String areaName;
    int cityId;
    String cityName;
    int provinceId;
    String provinceName;
    boolean isDefault;
    String mobile;
    String name;

    @Override
    public String toString() {
        return "AddressDetailWx{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", areaId=" + areaId +
                ", areaName='" + areaName + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", provinceId=" + provinceId +
                ", provinceName='" + provinceName + '\'' +
                ", isDefault=" + isDefault +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
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

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(boolean isDefault) {
        this.isDefault = isDefault;
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
