package com.cskaoyan.mall.service.wechat;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.wxfbean.AddressDetailWx;
import com.cskaoyan.mall.bean.wxfbean.AddressInfoWx;
import com.cskaoyan.mall.bean.wxfbean.AddressSimpleWx;

import java.util.List;

public interface AddressWxService {
    List<AddressSimpleWx> queryAddressList();
    AddressDetailWx queryAddressDetail(int id);
    int insertAddress(Address address);

    int deleteAddress(int id);
}
