package com.cskaoyan.mall.service;
/*国旭*/
import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapper userMapper;
    @Autowired
    AddressMapper addressMapper;
    @Autowired
    CollectMapper collectMapper;
    @Autowired
    FootprintMapper footprintMapper;
    @Autowired
    SearchHistoryMapperGuo searchHistoryMapper;
    @Autowired
    FeedbackMapperGuo feedbackMapper;

    @Override
    public List<User> getUserList() {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andIdIsNotNull().andDeletedEqualTo(false);
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    public static UserGuo transferUserToUserGuo(User user) {
        UserGuo userGuo = new UserGuo();
        userGuo.setId(user.getId());
        userGuo.setUsername(user.getUsername());
        userGuo.setPassword(user.getPassword());
        userGuo.setGender(user.getGender());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String birthday = simpleDateFormat.format(user.getBirthday());
        userGuo.setBirthday(birthday);
        userGuo.setLastLoginIp(user.getLastLoginIp());
        String lastLogininTime = simpleDateFormat.format(user.getLastLoginTime());
        userGuo.setLastLoginTime(lastLogininTime);
        userGuo.setUserLevel(user.getUserLevel());
        userGuo.setNickname(user.getNickname());
        userGuo.setMobile(user.getMobile());
        userGuo.setAvatar(user.getAvatar());
        userGuo.setWeixinOpenid(user.getWeixinOpenid());
        userGuo.setStatus(user.getStatus());
        String addTime = simpleDateFormat.format(user.getAddTime());
        userGuo.setAddTime(addTime);
        String updateTime = simpleDateFormat.format(user.getUpdateTime());
        userGuo.setUpdateTime(updateTime);
        userGuo.setDeleted(user.getDeleted());
        return userGuo;
    }

    @Override
    public List<Address> getAddressList() {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andIdIsNotNull().andDeletedEqualTo(false);
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }

    @Override
    public List<Collect> getConlectList() {
        CollectExample collectExample=new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andIdIsNotNull().andDeletedEqualTo(false);
        List<Collect> collects=collectMapper.selectByExample(collectExample);
        return collects;
    }

    public static CollectGuo transferCollectToCollectGuo(Collect collect) {
        CollectGuo collectGuo = new CollectGuo();
        collectGuo.setId(collect.getId());
        collectGuo.setUserId(collect.getUserId());
        collectGuo.setValueId(collect.getValueId());
        collectGuo.setType(collect.getType());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTime = simpleDateFormat.format(collect.getAddTime());
        collectGuo.setAddTime(addTime);
        collectGuo.setDeleted(collect.getDeleted());
        String updateTime = simpleDateFormat.format(collect.getUpdateTime());
        collectGuo.setUpdateTime(updateTime);
        return collectGuo;
    }

    @Override
    public List<Footprint> getFootPrintList() {
        FootprintExample footprintExample=new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        criteria.andIdIsNotNull().andDeletedEqualTo(false);
        List<Footprint> footprints = footprintMapper.selectByExample(footprintExample);
        return footprints;
    }

    public static FootprintGuo transferFootprintToFootprintGuo(Footprint footprint) {
        FootprintGuo footprintGuo = new FootprintGuo();
        footprintGuo.setId(footprint.getId());
        footprintGuo.setUserId(footprint.getUserId());
        footprintGuo.setGoodsId(footprint.getGoodsId());
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String addTime = simpleDateFormat.format(footprint.getAddTime());
        footprintGuo.setAddTime(addTime);
        String updateTime = simpleDateFormat.format(footprint.getUpdateTime());
        footprintGuo.setUpdateTime(updateTime);
        footprintGuo.setDeleted(footprint.getDeleted());
        return footprintGuo;
    }

    @Override
    public List<SearchHistoryGuo> getSearchHistory() {
        List<SearchHistoryGuo> searchHistoryList = searchHistoryMapper.getSearchHistoryList();
        for (SearchHistoryGuo searchHistory : searchHistoryList) {
            searchHistory.setUserId(searchHistoryMapper.getUserIdById(searchHistory.getId()));
            searchHistory.setAddTime(searchHistoryMapper.getAddTimeById(searchHistory.getId()));
        }
        return searchHistoryList;
    }

    @Override
    public List<FeedbackGuo> getFeedbackList() {
        List<FeedbackGuo> feedbackList = feedbackMapper.getFeedbackList();
        return feedbackList;
    }

    @Override
    public List<User> getUserListByScreen(String mobile, String username) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        if (mobile==null){
            criteria.andUsernameLike("%"+username+"%").andDeletedEqualTo(false);
        }else if(username==null){
            criteria.andMobileLike("%"+mobile+"%").andDeletedEqualTo(false);
        }else {
            criteria.andUsernameLike("%"+username+"%").andMobileLike("%"+mobile+"%").andDeletedEqualTo(false);
        }
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public List<Address> getAddressListByScreen(Integer userId, String name) {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        if(userId==null){
            criteria.andNameLike("%"+name+"%").andDeletedEqualTo(false);
        }else if (name==null){
            criteria.andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else {
            criteria.andNameLike("%"+name+"%").andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }

    @Override
    public List<Collect> getCollectListByScreen(Integer valueId, Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if(valueId!=null&&userId!=null){
            criteria.andValueIdEqualTo(valueId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else if(userId==null&&valueId!=null){
            criteria.andValueIdEqualTo(valueId).andDeletedEqualTo(false);
        }else{
            criteria.andValueIdEqualTo(userId).andDeletedEqualTo(false);
        }
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        return collects;
    }

    @Override
    public List<Footprint> getFootprintByScreen(Integer goodsId, Integer userId) {
        FootprintExample footprintExample = new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        if(goodsId!=null&&userId!=null){
            criteria.andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else if(goodsId==null&&userId!=null){
            criteria.andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else {
            criteria.andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        }
        List<Footprint> footprints = footprintMapper.selectByExample(footprintExample);
        return footprints;
    }

    @Override
    public List<SearchHistoryGuo> getSearchHistoryByScreen(Integer userId, String keyword) {
        List<SearchHistoryGuo> searchHistoryListByScreen = searchHistoryMapper.getSearchHistoryListByScreen(userId, keyword);
        return searchHistoryListByScreen;
    }

    @Override
    public List<FeedbackGuo> getFeedbackListByScreen(Integer id, String username) {
        return feedbackMapper.getFeedbackListBySreen(id,username);
    }
}
