package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.UserServiceImpl;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/*国旭的*/
@Controller
@RequestMapping("/admin")
public class UserController {


    @Autowired
    UserServiceImpl userService;


    @RequestMapping("/user/list")
    @ResponseBody
    public BaseRespVo getUserList(UserShow userShow){
        List<User> userList=null;
        if(userShow.getUsername()!=null||userShow.getMobile()!=null){
            PageHelper.startPage(userShow.getPage(),userShow.getLimit(),userShow.getSort()+" "+userShow.getOrder());
            userList= userService.getUserListByScreen(userShow.getMobile(), userShow.getUsername());
        }else {
            PageHelper.startPage(userShow.getPage(),userShow.getLimit(),userShow.getSort()+" "+userShow.getOrder());
            userList = userService.getUserList();
        }
        List<UserGuo> userGuos=new ArrayList<>();
        for (User user : userList) {
            UserGuo userGuo= UserServiceImpl.transferUserToUserGuo(user);
            userGuos.add(userGuo);
        }
        /*PageInfo<User> userPageInfo=new PageInfo<>(userList);
        long total = userPageInfo.getTotal();
        ItemsList<UserGuo> users = new ItemsList<>();
        users.setItems(userGuos);
        users.setTotal(total);*/
        ItemsList<UserGuo> userGuoItemsList = itemsList1(userList, userGuos);
        BaseRespVo ok = BaseRespVo.ok(userGuoItemsList);
        return ok;
    }
    @RequestMapping("/address/list")
    @ResponseBody
    @RequiresPermissions("admin:address:list")
    public BaseRespVo getAddress(AdressShow adressShow){
        String userId = (String)adressShow.getUserId();
        if(userId!=null&&!"".equals(userId)&&!userId.matches("[0-9]+")){
            return fail("输入不合法",5);
        }
        if(userId!=null&&!"".equals(userId)&&userId.length()>9){
            return fail("输入不合法",5);
        }
        PageHelper.startPage(adressShow.getPage(),adressShow.getLimit(),adressShow.getSort()+" "+adressShow.getOrder());
        List<Address> addressList = userService.getAddressList(adressShow);
        List<GuoAddress> guoAddressList = userService.getGuoAddressList(addressList);
        ItemsList<GuoAddress> guoAddressItemsList = itemsList1(addressList, guoAddressList);
        BaseRespVo ok = BaseRespVo.ok(guoAddressItemsList);
        return ok;
    }
    @RequestMapping("/collect/list")
    @ResponseBody
    public BaseRespVo getCollectList(CollectShow collectShow){
        String userId = (String)collectShow.getUserId();
        String valueId = (String)collectShow.getValueId();

        if(  (userId!=null&&!"".equals(userId) && !userId.matches("[0-9]+") )
                ||(valueId!=null&&!"".equals(valueId)&& !valueId.matches("[0-9]+")) ){
            return fail("输入不合法",4);
        }
        if(
                (userId!=null&&!"".equals(userId)&&userId.length()>9)
                ||(valueId!=null&&!"".equals(valueId)&&valueId.length()>9)
        ){
            return fail("输入不合法",4);
        }
        PageHelper.startPage(collectShow.getPage(),collectShow.getLimit(),collectShow.getSort()+" "+collectShow.getOrder());
        List<Collect> collectList=userService.getConlectList(collectShow);
        ItemsList<Collect> collectItemsList = itemsList(collectList);
        BaseRespVo ok = BaseRespVo.ok(collectItemsList);
        return ok;
    }
    @RequestMapping("/footprint/list")
    @ResponseBody
    @RequiresPermissions("admin:footprint:list")
    public BaseRespVo getFootPrintList(FootprintShow footprintShow){
        String userId = (String)footprintShow.getUserId();
        String goodsId = (String)footprintShow.getGoodsId();
        if(  (userId!=null&&!"".equals(userId) && !userId.matches("[0-9]+") )
                ||(goodsId!=null&&!"".equals(goodsId)&& !goodsId.matches("[0-9]+")) ){
            return fail("输入不合法",3);
        }
        if(
                (userId!=null&&!"".equals(userId)&&userId.length()>9)
                ||(goodsId!=null&&!"".equals(goodsId)&&goodsId.length()>9)
        ){
            return fail("输入不合法",3);
        }
        PageHelper.startPage(footprintShow.getPage(),footprintShow.getLimit(),footprintShow.getSort()+" "+footprintShow.getOrder());
        List<Footprint> footprintList=userService.getFootPrintList(footprintShow);
        ItemsList<Footprint> footprintItemsList = itemsList(footprintList);
        BaseRespVo ok = BaseRespVo.ok(footprintItemsList);
        return ok;
    }
    @RequestMapping("/history/list")
    @ResponseBody
    public BaseRespVo getSearchHistoryList(SearchHistoryShow searchHistoryShow){
        String userId = (String) searchHistoryShow.getUserId();
        if(userId!=null&&!"".equals(userId)&&!userId.matches("[0-9]+")){
            return fail("输入不合法",2);
        }
        if(userId!=null&&!"".equals(userId)&&userId.length()>9){
            return fail("输入不合法",2);
        }
        PageHelper.startPage(searchHistoryShow.getPage(),searchHistoryShow.getLimit(),searchHistoryShow.getSort()+" "+searchHistoryShow.getOrder());
        List<SearchHistory> searchHistoryList=userService.getSearchHistoryList(searchHistoryShow);
        ItemsList<SearchHistory> searchHistoryItemsList = itemsList(searchHistoryList);
        BaseRespVo ok = BaseRespVo.ok(searchHistoryItemsList);
        return ok;
    }
    @RequestMapping("/feedback/list")
    @ResponseBody
    @RequiresPermissions("admin:feedback:list")
    public BaseRespVo getFeedbackList(FeedbackShow feedbackShow){
        String id  = (String)feedbackShow.getId();
        if(id!=null&&!"".equals(id)&&!id.matches("[0-9]+")){
            return fail("输入不合法",1);
        }
        if(id!=null&&!"".equals(id)&&id.length()>9){
            return fail("输入不合法",1);
        }
        PageHelper.startPage(feedbackShow.getPage(),feedbackShow.getLimit(),feedbackShow.getSort()+" "+feedbackShow.getOrder());
        List<Feedback> feedbackList=userService.getFeedbackList(feedbackShow);
        ItemsList<Feedback> feedbackGuoItemsList = itemsList(feedbackList);
        BaseRespVo ok = BaseRespVo.ok(feedbackGuoItemsList);
        return ok;
    }

    public static BaseRespVo fail(String msg, int errno){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(null);
        baseRespVo.setErrno(errno);
        baseRespVo.setErrmsg(msg);
        return baseRespVo;
    }
    public static <T> ItemsList<T> itemsList(List<T> list){
        PageInfo<T> pageInfo=new PageInfo<>(list);
        long total = pageInfo.getTotal();
        ItemsList<T> itemsList=new ItemsList<>();
        itemsList.setTotal(total);
        itemsList.setItems(list);
        return itemsList;
    }
    private static <T,V> ItemsList<V> itemsList1(List<T> list, List<V> vList){
        PageInfo<T> pageInfo=new PageInfo<>(list);
        long total = pageInfo.getTotal();
        ItemsList<V> itemsList=new ItemsList<>();
        itemsList.setTotal(total);
        itemsList.setItems(vList);
        return itemsList;
    }




    /*@RequestMapping("/address/list")
    @ResponseBody
    public BaseRespVo getAddress(AdressShow adressShow){
        String str=(String) adressShow.getUserId();
        if (str==null||"".equals(str)||str.matches("[0-9]")){
            PageHelper.startPage(adressShow.getPage(),adressShow.getLimit(),adressShow.getSort()+" "+adressShow.getOrder());
            List<Address> addressList =null;
            if((adressShow.getName()!=null&&adressShow.getName()!="")||(adressShow.getUserId()!=null&&adressShow.getUserId()!="")) {
                addressList=userService.getAddressListByScreen((Integer) adressShow.getUserId(),adressShow.getName());
            }else {
                addressList=userService.getAddressList();
            }

            ItemsList<Address> addressItemsList = itemsList(addressList);
            BaseRespVo ok = BaseRespVo.ok(addressItemsList);
            return ok;
        }else {
            BaseRespVo objectBaseRespVo = new BaseRespVo();
            objectBaseRespVo.setData(null);
            objectBaseRespVo.setErrno(1);
            objectBaseRespVo.setErrmsg("输入不规范");
            return objectBaseRespVo;
        }
        *//*PageHelper.startPage(adressShow.getPage(),adressShow.getLimit(),adressShow.getSort()+" "+adressShow.getOrder());
        List<Address> addressList =null;
        if(adressShow.getName()!=null||adressShow.getUserId()!=null) {
            addressList=userService.getAddressListByScreen((Integer) adressShow.getUserId(),adressShow.getName());
        }else {
            addressList=userService.getAddressList();
        }

        ItemsList<Address> addressItemsList = itemsList(addressList);
        BaseRespVo ok = BaseRespVo.ok(addressItemsList);
        return ok;*//*
    }

    @RequestMapping("/collect/list")
    @ResponseBody
    public BaseRespVo getCollectList(CollectShow collectShow){
        String userId=(String) collectShow.getUserId();
        String valueId=(String) collectShow.getValueId();
        if(userId==null||"".equals(userId)||userId.matches("[0-9]")||valueId!=null||"".equals(valueId)||valueId.matches("[0-9]")){
            PageHelper.startPage(collectShow.getPage(),collectShow.getLimit(),collectShow.getSort()+" "+collectShow.getOrder());
            List<Collect> collects=null;
            if(collectShow.getUserId()!=null||collectShow.getValueId()!=null){
                collects=userService.getCollectListByScreen((Integer) collectShow.getValueId(),(Integer) collectShow.getUserId());
            }else {
                collects=userService.getConlectList();
            }
            List<CollectGuo> collectGuos=new ArrayList<>();
            for (Collect collect : collects) {
                CollectGuo collectGuo=UserServiceImpl.transferCollectToCollectGuo(collect);
                collectGuos.add(collectGuo);
            }
            ItemsList<CollectGuo> collectGuoItemsList = itemsList1(collects, collectGuos);
            BaseRespVo ok = BaseRespVo.ok(collectGuoItemsList);
            return ok;
        }else {
            BaseRespVo objectBaseRespVo = new BaseRespVo();
            objectBaseRespVo.setData(null);
            objectBaseRespVo.setErrno(1);
            objectBaseRespVo.setErrmsg("输入不规范");
            return objectBaseRespVo;
        }
    }

    @RequestMapping("/footprint/list")
    @ResponseBody
    public BaseRespVo getFootPrintList(FootprintShow footprintShow){
        PageHelper.startPage(footprintShow.getPage(),footprintShow.getLimit(),footprintShow.getSort()+" "+footprintShow.getOrder());
        List<Footprint> footPrintList =null;
        if(footprintShow.getUserId()!=null||footprintShow.getGoodsId()!=null){
            footPrintList=userService.getFootprintByScreen(footprintShow.getGoodsId(),footprintShow.getUserId());
        }else {
            footPrintList=userService.getFootPrintList();
        }
        List<FootprintGuo> footprintGuos=new ArrayList<>();
        for (Footprint footprint : footPrintList) {
            FootprintGuo footprintGuo=UserServiceImpl.transferFootprintToFootprintGuo(footprint);
            footprintGuos.add(footprintGuo);
        }
        ItemsList<FootprintGuo> footprintGuoItemsList = itemsList1(footPrintList, footprintGuos);
        BaseRespVo ok = BaseRespVo.ok(footprintGuoItemsList);
        return ok;
    }
    @RequestMapping("/history/list")
    @ResponseBody
    public BaseRespVo getSearchHistoryList(SearchHistoryShow searchHistoryShow){
        PageHelper.startPage(searchHistoryShow.getPage(), searchHistoryShow.getLimit(),searchHistoryShow.getSort()+" "+searchHistoryShow.getOrder());
        List<SearchHistoryGuo> searchHistoryList = null;
        if(searchHistoryShow.getKeyword()!=null||searchHistoryShow.getUserId()!=null){
            searchHistoryList=userService.getSearchHistoryByScreen(searchHistoryShow.getUserId(),searchHistoryShow.getKeyword());
        }else {
            searchHistoryList=userService.getSearchHistory();
        }
        ItemsList<SearchHistoryGuo> searchHistoryItemsList = itemsList(searchHistoryList);
        BaseRespVo ok = BaseRespVo.ok(searchHistoryItemsList);
        return ok;
    }
    @RequestMapping("/feedback/list")
    @ResponseBody
    public BaseRespVo getFeedbackList(FeedbackShow feedbackShow){
        PageHelper.startPage(feedbackShow.getPage(),feedbackShow.getLimit(),feedbackShow.getSort()+" "+feedbackShow.getOrder());
        List<FeedbackGuo> feedbackList =null;
        if(feedbackShow.getId()!=null||feedbackShow.getUsername()!=null){
            feedbackList=userService.getFeedbackListByScreen(feedbackShow.getId(),feedbackShow.getUsername());
        }else {
            feedbackList=userService.getFeedbackList();
        }
        ItemsList<FeedbackGuo> feedbackItemsList = itemsList(feedbackList);
        BaseRespVo ok = BaseRespVo.ok(feedbackItemsList);
        return ok;
    }*/
}
