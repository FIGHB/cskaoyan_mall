package com.cskaoyan.mall.service.wechat;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.wxfbean.AddressDetailWx;
import com.cskaoyan.mall.bean.wxfbean.AddressSimpleWx;

import java.util.List;

public interface AddressWxService {
    List<AddressSimpleWx> queryAddressList(String username);
    AddressDetailWx queryAddressDetail(int id);
    int saveAddress(Address address,String username);

    int deleteAddress(int id,String username);
}
