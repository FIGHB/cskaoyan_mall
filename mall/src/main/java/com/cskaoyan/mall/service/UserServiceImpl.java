package com.cskaoyan.mall.service;
/*国旭*/
import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.FeedbackGuo;
import com.cskaoyan.mall.vo.SearchHistoryGuo;
import com.cskaoyan.mall.vo.SearchHistoryShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        criteria.andIdIsNotNull();
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public List<Address> getAddressList() {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }

    @Override
    public List<Collect> getConlectList() {
        CollectExample collectExample=new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Collect> collects=collectMapper.selectByExample(collectExample);
        return collects;
    }

    @Override
    public List<Footprint> getFootPrintList() {
        FootprintExample footprintExample=new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        criteria.andIdIsNotNull();
        List<Footprint> footprints = footprintMapper.selectByExample(footprintExample);
        return footprints;
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
            criteria.andUsernameLike("%"+username+"%");
        }else if(username==null){
            criteria.andMobileLike("%"+mobile+"%");
        }else {
            criteria.andUsernameLike("%"+username+"%").andMobileLike("%"+mobile+"%");
        }
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public List<Address> getAddressListByScreen(Integer userId, String name) {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        if(userId==null){
            criteria.andNameLike("%"+name+"%");
        }else if (name==null){
            criteria.andUserIdEqualTo(userId);
        }else {
            criteria.andNameLike("%"+name+"%").andUserIdEqualTo(userId);
        }
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }

    @Override
    public List<Collect> getCollectListByScreen(Integer valueId, Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if(valueId!=null&&userId!=null){
            criteria.andValueIdEqualTo(valueId).andUserIdEqualTo(userId);
        }else if(userId==null&&valueId!=null){
            criteria.andValueIdEqualTo(valueId);
        }else{
            criteria.andValueIdEqualTo(userId);
        }
        List<Collect> collects = collectMapper.selectByExample(collectExample);
        return collects;
    }

    @Override
    public List<Footprint> getFootprintByScreen(Integer goodsId, Integer userId) {
        FootprintExample footprintExample = new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        if(goodsId!=null&&userId!=null){
            criteria.andGoodsIdEqualTo(goodsId).andUserIdEqualTo(userId);
        }else if(goodsId==null&&userId!=null){
            criteria.andUserIdEqualTo(userId);
        }else {
            criteria.andGoodsIdEqualTo(goodsId);
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
