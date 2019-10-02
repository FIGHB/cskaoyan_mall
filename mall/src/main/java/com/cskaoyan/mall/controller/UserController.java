package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.service.UserServiceImpl;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    UserService userService;

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
    public BaseRespVo getAddress(AdressShow adressShow){
        PageHelper.startPage(adressShow.getPage(),adressShow.getLimit(),adressShow.getSort()+" "+adressShow.getOrder());
        List<Address> addressList =null;
        if(adressShow.getName()!=null||adressShow.getUserId()!=null) {
            addressList=userService.getAddressListByScreen(adressShow.getUserId(),adressShow.getName());
        }else {
            addressList=userService.getAddressList();
        }

        ItemsList<Address> addressItemsList = itemsList(addressList);
        BaseRespVo ok = BaseRespVo.ok(addressItemsList);
        return ok;
    }

    @RequestMapping("/collect/list")
    @ResponseBody
    public BaseRespVo getCollectList(CollectShow collectShow){
        PageHelper.startPage(collectShow.getPage(),collectShow.getLimit(),collectShow.getSort()+" "+collectShow.getOrder());
        List<Collect> collects=null;
        if(collectShow.getUserId()!=null||collectShow.getValueId()!=null){
            collects=userService.getCollectListByScreen(collectShow.getValueId(),collectShow.getUserId());
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
}
