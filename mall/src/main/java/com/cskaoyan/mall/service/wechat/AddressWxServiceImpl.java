package com.cskaoyan.mall.service.wechat;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.wxfbean.AddressDetailWx;
import com.cskaoyan.mall.bean.wxfbean.AddressInfoWx;
import com.cskaoyan.mall.bean.wxfbean.AddressSimpleWx;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.wechat.AddressWxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.util.List;

@Service
public class AddressWxServiceImpl implements AddressWxService{
    @Autowired
    AddressWxMapper addressWxMapper;
    @Autowired
    AddressMapper addressMapper;
    @Override
    public List<AddressSimpleWx> queryAddressList() {
        List<AddressSimpleWx> addressList = addressWxMapper.queryAllAddress();
        return addressList;
    }

    @Override
    public AddressDetailWx queryAddressDetail(int id) {
        AddressDetailWx addressInfo = addressWxMapper.queryAddressDetail(id);
        //设置区域名字
        int areaId = addressInfo.getAreaId();
        String areaName = addressWxMapper.queryRegionNameById(areaId);
        addressInfo.setAreaName(areaName);
        //设置城市名称
        addressInfo.setCityName(addressWxMapper.queryRegionNameById(addressInfo.getCityId()));
        addressInfo.setProvinceName(addressWxMapper.queryRegionNameById(addressInfo.getProvinceId()));
        return addressInfo;
    }

    @Override
    public int insertAddress(Address address) {
            /*int insertAddress = addressWxMapper.insertAddress(id,name,provinceId,cityId,areaId,address,mobile,isDefault);*/
        int i = addressMapper.insertSelective(address);
        Address address1 = addressWxMapper.selectLastId();/*System.out.println(insertAddress);*/

        return address1.getId();
    }

    @Override
    public int deleteAddress(int id) {
        int deleteNum = addressMapper.deleteByPrimaryKey(id);
        return deleteNum;
    }
}
