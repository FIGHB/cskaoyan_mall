package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.service.UserService;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
            PageHelper.startPage(userShow.getPage(),userShow.getLimit());
            userList= userService.getUserListByScreen(userShow.getMobile(), userShow.getUsername());
        }else {
            PageHelper.startPage(userShow.getPage(),userShow.getLimit());
            userList = userService.getUserList();
            /*PageInfo<User> userPageInfo=new PageInfo<>(userList);
            long total = userPageInfo.getTotal();
            ItemsList<User> users = new ItemsList<>();
            users.setItems(userList);
            users.setTotal(total);*/
        }
        ItemsList<User> userItemsList = itemsList(userList);
        BaseRespVo ok = BaseRespVo.ok(userItemsList);
        return ok;
    }

    @RequestMapping("/address/list")
    @ResponseBody
    public BaseRespVo getAddress(AdressShow adressShow){
        PageHelper.startPage(adressShow.getPage(),adressShow.getLimit());
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
        PageHelper.startPage(collectShow.getPage(),collectShow.getLimit());
        List<Collect> collects=null;
        if(collectShow.getUserId()!=null||collectShow.getValueId()!=null){
            collects=userService.getCollectListByScreen(collectShow.getValueId(),collectShow.getUserId());
        }else {
            collects=userService.getConlectList();
        }
        ItemsList<Collect> collectItemsList = itemsList(collects);
        BaseRespVo ok = BaseRespVo.ok(collectItemsList);
        return ok;
    }
    @RequestMapping("/footprint/list")
    @ResponseBody
    public BaseRespVo getFootPrintList(FootprintShow footprintShow){
        PageHelper.startPage(footprintShow.getPage(),footprintShow.getLimit());
        List<Footprint> footPrintList =null;
        if(footprintShow.getUserId()!=null||footprintShow.getGoodsId()!=null){
            footPrintList=userService.getFootprintByScreen(footprintShow.getGoodsId(),footprintShow.getUserId());
        }else {
            footPrintList=userService.getFootPrintList();
        }
        ItemsList<Footprint> footprintItemsList = itemsList(footPrintList);
        BaseRespVo ok = BaseRespVo.ok(footprintItemsList);
        return ok;
    }
    @RequestMapping("/history/list")
    @ResponseBody
    public BaseRespVo getSearchHistoryList(SearchHistoryShow searchHistoryShow){
        PageHelper.startPage(searchHistoryShow.getPage(), searchHistoryShow.getLimit());
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
        PageHelper.startPage(feedbackShow.getPage(),feedbackShow.getLimit());
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
    private static <T> ItemsList<T> itemsList(List<T> list){
        PageInfo<T> pageInfo=new PageInfo<>(list);
        long total = pageInfo.getTotal();
        ItemsList<T> itemsList=new ItemsList<>();
        itemsList.setTotal(total);
        itemsList.setItems(list);
        return itemsList;
    }
}
