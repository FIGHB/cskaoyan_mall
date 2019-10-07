package com.cskaoyan.mall.controller.wechat;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.wxfbean.AddressDetailWx;
import com.cskaoyan.mall.bean.wxfbean.AddressInfoWx;
import com.cskaoyan.mall.bean.wxfbean.AddressSimpleWx;
import com.cskaoyan.mall.service.wechat.AddressWxService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 赵亚云
 */

@RestController
public class AddressWxZhaoController {
    @Autowired
    AddressWxService addressWxService;

    @RequestMapping("/wx/address/list")
    public BaseRespVo addressListShowWx(){
        List<AddressSimpleWx> data = addressWxService.queryAddressList();
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    @RequestMapping("/wx/address/detail")
    public BaseRespVo addressDetailQuery(int id){
        System.out.println(id);
        AddressDetailWx data = addressWxService.queryAddressDetail(id);
        BaseRespVo baseRespVo = BaseRespVo.ok(data);
        return baseRespVo;
    }

    @RequestMapping("/wx/address/save")
    public BaseRespVo addressInsert(@RequestBody Address address){
        return BaseRespVo.ok(addressWxService.insertAddress(address));
    }

    @RequestMapping("/wx/address/delete")
    public BaseRespVo addressDelete(@RequestBody Address address){

        int deleteNum = addressWxService.deleteAddress(address.getId());
        if (deleteNum>0){
            return BaseRespVo.ok(null);
        }
        return BaseRespVo.fail(500,"删除地址失败");
    }
}
