package com.cskaoyan.mall.service.wechat;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.wxfbean.AddressDetailWx;
import com.cskaoyan.mall.bean.wxfbean.AddressSimpleWx;
import com.cskaoyan.mall.mapper.AddressMapper;
import com.cskaoyan.mall.mapper.wechat.AddressWxMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressWxServiceImpl implements AddressWxService {
    @Autowired
    AddressWxMapper addressWxMapper;
    @Autowired
    AddressMapper addressMapper;

    @Override
    public List<AddressSimpleWx> queryAddressList(String username) {
        int userId = addressWxMapper.queryUserId(username);
        List<Address> addressList = addressWxMapper.queryAllAddress(userId);
        int count = 0;
        for (Address address : addressList) {
            if (address.getIsDefault() == true) {
                count++;
            }
        }
        if (count == 0) {
            int update = addressList.get(0).getId();
            addressWxMapper.updateIsDefault(update, true);
        }
        List<AddressSimpleWx> addressListShow = addressWxMapper.queryAllAddressSimple(userId);
        return addressListShow;
    }

    @Override
    public AddressDetailWx queryAddressDetail(int id) {
        AddressDetailWx addressInfo = addressWxMapper.queryAddressDetail(id);
        System.out.println(addressInfo);
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
    public int saveAddress(Address address, String username) {
        int userId = addressWxMapper.queryUserId(username);
        int id;
        List<Address> addressList = addressWxMapper.queryAllAddress(userId);
        //判断，如果保存的数据已经在数据库中，那么就 只修改就好，如果不再数据库中，要插入数据
        Address toAddress = addressMapper.selectByPrimaryKey(address.getId());
        id = address.getId();
        if (toAddress != null) {
            address.setUpdateTime(new Date());
            if (address.getIsDefault()) {
                for (Address address1 : addressList) {
                    if (address1.getIsDefault()) {
                            addressWxMapper.updateIsDefault(address1.getId(), false);
                    }
                }
                int update = addressWxMapper.updateAddress(address);
            }
        } else {
            address.setUserId(userId);
            Date date = new Date();
            address.setAddTime(date);
            address.setUpdateTime(date);
//            int update = addressWxMapper.insertAddress(address);
//            if(update > 0)
//            return addressWxMapper.selectLastId().getId();
            int i = addressMapper.insertSelective(address);
            Address address1 = addressWxMapper.selectLastId();/*System.out.println(insertAddress);*/
            id = address1.getId();
            if (address.getIsDefault()) {
                for (Address address2 : addressList) {
                    if (address2.getIsDefault()) {
                        int updateId = address2.getId();
                        if (id != updateId) {
                            addressWxMapper.updateIsDefault(updateId, false);
                        }
                    }
                }
            }
        }
        return id;
    }

    @Override
    public int deleteAddress(int id, String username) {
        int userId = addressWxMapper.queryUserId(username);
        Address address = addressMapper.selectByPrimaryKey(id);
        int deleteNum;
        if (address != null && address.getIsDefault()) {
            List<Address> addressList = addressWxMapper.queryAllAddress(userId);
            int update = addressList.get(0).getId();
            int i = addressWxMapper.updateIsDefault(update, true);
        }
        deleteNum = addressMapper.deleteByPrimaryKey(id);
        return deleteNum;
    }
}

