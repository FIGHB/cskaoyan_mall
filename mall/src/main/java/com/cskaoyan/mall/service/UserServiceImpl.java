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
    SearchHistoryMapper searchHistoryMapper;
    @Autowired
    FeedbackMapper feedbackMapper;
    @Autowired
    RegionMapper regionMapper;
    @Autowired
    GuoRegionMapper guoRegionMapper;

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
    public List<Address> getAddressList(AdressShow adressShow) {
        AddressExample addressExample = new AddressExample();
        AddressExample.Criteria criteria = addressExample.createCriteria();
        if((adressShow.getUserId()!=null&&!"".equals(adressShow.getUserId()))&&(adressShow.getName()!=null&&!"".equals(adressShow.getName()))){
            String userIdStr = (String) adressShow.getUserId();
            int userId = Integer.parseInt(userIdStr);
            criteria.andUserIdEqualTo(userId).andNameLike("%"+adressShow.getName()+"%").andDeletedEqualTo(false);
        }else if((adressShow.getUserId()==null||"".equals(adressShow.getUserId()))&&(adressShow.getName()!=null&&!"".equals(adressShow.getName()))){
            criteria.andNameLike("%"+adressShow.getName()+"%").andDeletedEqualTo(false);
        }else if((adressShow.getUserId()!=null&&!"".equals(adressShow.getUserId()))&&(adressShow.getName()==null||"".equals(adressShow.getName()))){
            String userIdStr=(String)adressShow.getUserId();
            int userId = Integer.parseInt(userIdStr);
            criteria.andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
        List<Address> addresses = addressMapper.selectByExample(addressExample);
        return addresses;
    }
    public List<GuoAddress> getGuoAddressList(List<Address> addresses){
        List<GuoAddress> guoAddressList=new ArrayList<>();
        for (Address address : addresses) {
            GuoAddress guoAddress=new GuoAddress();

            Region region1 = guoRegionMapper.getRegionById(address.getAreaId());
            guoAddress.setArea(region1.getName());

            Region region2 = guoRegionMapper.getRegionById(address.getCityId());
            guoAddress.setCity(region2.getName());

            Region region3 = guoRegionMapper.getRegionById(address.getProvinceId());
            guoAddress.setProvince(region3.getName());

            guoAddress.setDeleted(false);
            guoAddress.setIsDefault(address.getIsDefault());
            guoAddress.setAreaId(address.getAreaId());
            guoAddress.setAddress(address.getAddress());
            guoAddress.setName(address.getName());
            guoAddress.setMobile(address.getMobile());
            guoAddress.setId(address.getId());
            guoAddress.setCityId(address.getCityId());
            guoAddress.setProvinceId(address.getProvinceId());
            guoAddress.setUserId(address.getUserId());

            guoAddressList.add(guoAddress);
        }
        return guoAddressList;
    }

    @Override
    public List<Collect> getConlectList(CollectShow collectShow) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if( (collectShow.getValueId()!=null&&!"".equals(collectShow.getValueId()) )
        &&( collectShow.getUserId()!=null&&!"".equals(collectShow.getUserId()) ) ){
            String valueIdStr = (String) collectShow.getValueId();
            int valueId = Integer.parseInt(valueIdStr);
            String userIdStr = (String)collectShow.getUserId();
            int userId = Integer.parseInt(userIdStr);
            criteria.andValueIdEqualTo(valueId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else if( collectShow.getValueId()!=null&&!"".equals(collectShow.getValueId())
            &&(collectShow.getUserId()==null||"".equals(collectShow.getUserId())) ){
            String valueIdStr = (String)collectShow.getValueId();
            int i = Integer.parseInt(valueIdStr);
            criteria.andValueIdEqualTo(i).andDeletedEqualTo(false);
        }else if( collectShow.getUserId()!=null&&!"".equals(collectShow.getUserId())
            &&(collectShow.getValueId()==null||"".equals(collectShow.getValueId()))){
            String userIdStr = (String)collectShow.getUserId();
            int userId = Integer.parseInt(userIdStr);
            criteria.andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
        List<Collect> collects = collectMapper.selectByExample(collectExample);
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
    public List<Footprint> getFootPrintList(FootprintShow footprintShow) {
        FootprintExample footprintExample=new FootprintExample();
        FootprintExample.Criteria criteria = footprintExample.createCriteria();
        if(
           footprintShow.getUserId()!=null&&!"".equals(footprintShow.getUserId())
           &&footprintShow.getGoodsId()!=null&&!"".equals(footprintShow.getGoodsId())
        ){
            String goodsIdStr = (String)footprintShow.getGoodsId();
            int goodsId = Integer.parseInt(goodsIdStr);
            String userIdStr =(String) footprintShow.getUserId();
            int i = Integer.parseInt(userIdStr);
            criteria.andGoodsIdEqualTo(goodsId).andUserIdEqualTo(i).andDeletedEqualTo(false);
        }else if(
            footprintShow.getUserId()!=null&&!"".equals(footprintShow.getUserId())
            &&(footprintShow.getGoodsId()==null||"".equals(footprintShow.getGoodsId()))
        ){
            String userIdStr =(String) footprintShow.getUserId();
            int i = Integer.parseInt(userIdStr);
            criteria.andUserIdEqualTo(i).andDeletedEqualTo(false);
        }else if(
            footprintShow.getGoodsId()!=null&&!"".equals(footprintShow.getGoodsId())
            &&(footprintShow.getUserId()==null||"".equals(footprintShow.getUserId()))
        ){
            String goodsIdStr = (String)footprintShow.getGoodsId();
            int goodsId = Integer.parseInt(goodsIdStr);
            criteria.andGoodsIdEqualTo(goodsId).andDeletedEqualTo(false);
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
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
    public List<SearchHistory> getSearchHistoryList(SearchHistoryShow searchHistoryShow) {
        SearchHistoryExample searchHistoryExample = new SearchHistoryExample();
        SearchHistoryExample.Criteria criteria = searchHistoryExample.createCriteria();
        if(
            searchHistoryShow.getUserId()!=null&&!"".equals(searchHistoryShow.getUserId())
            &&searchHistoryShow.getKeyword()!=null&&!"".equals(searchHistoryShow.getKeyword())
        ){
            String userIdStr = (String) searchHistoryShow.getUserId();
            int userId = Integer.parseInt(userIdStr);
            criteria.andKeywordLike("%"+searchHistoryShow.getKeyword()+"%").andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else if(
                searchHistoryShow.getUserId()!=null&&!"".equals(searchHistoryShow.getUserId())
                &&(searchHistoryShow.getKeyword()==null||"".equals(searchHistoryShow.getKeyword()))
        ){
            String userIdStr = (String) searchHistoryShow.getUserId();
            int userId = Integer.parseInt(userIdStr);
            criteria.andDeletedEqualTo(false).andUserIdEqualTo(userId);
        }else if(
                searchHistoryShow.getKeyword()!=null&&!"".equals(searchHistoryShow.getKeyword())
                &&(searchHistoryShow.getUserId()==null||"".equals(searchHistoryShow.getUserId()))
        ){
            criteria.andDeletedEqualTo(false).andKeywordLike("%"+searchHistoryShow.getKeyword()+"%");
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
        List<SearchHistory> searchHistoryList = searchHistoryMapper.selectByExample(searchHistoryExample);
        return searchHistoryList;
    }

    @Override
    public List<Feedback> getFeedbackList(FeedbackShow feedbackShow) {
        FeedbackExample feedbackExample = new FeedbackExample();
        FeedbackExample.Criteria criteria = feedbackExample.createCriteria();
        if(
            feedbackShow.getId()!=null&&!"".equals(feedbackShow.getId())
            &&feedbackShow.getUsername()!=null&&!"".equals(feedbackShow.getUsername())
        ){
            String idStr = (String) feedbackShow.getId();
            int id = Integer.parseInt(idStr);
            criteria.andIdEqualTo(id).andUsernameLike("%"+feedbackShow.getUsername()+"%").andDeletedEqualTo(false);
        }else if(
            feedbackShow.getId()!=null&&!"".equals(feedbackShow.getId())
            &&(feedbackShow.getUsername()==null||"".equals(feedbackShow.getUsername()))
        ){
            String idStr = (String) feedbackShow.getId();
            int id = Integer.parseInt(idStr);
            criteria.andDeletedEqualTo(false).andIdEqualTo(id);
        }else if(
            feedbackShow.getUsername()!=null&&!"".equals(feedbackShow.getUsername())
            &&(feedbackShow.getId()==null||"".equals(feedbackShow.getId()))
        ){
            criteria.andDeletedEqualTo(false).andUsernameLike("%"+feedbackShow.getUsername()+"%");
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
        List<Feedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);
        return feedbacks;
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






    /*@Override
    public List<Collect> getCollectListByScreen(Integer valueId, Integer userId) {
        CollectExample collectExample = new CollectExample();
        CollectExample.Criteria criteria = collectExample.createCriteria();
        if((valueId!=null&&!"".equals(valueId))&&(userId!=null&&!"".equals(userId))){
            criteria.andValueIdEqualTo(valueId).andUserIdEqualTo(userId).andDeletedEqualTo(false);
        }else if((userId==null||"".equals(userId))&&(valueId!=null&&!"".equals(valueId))){
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
    }*/

    /*@Override
    public List<SearchHistoryGuo> getSearchHistoryByScreen(Integer userId, String keyword) {
        List<SearchHistoryGuo> searchHistoryListByScreen = searchHistoryMapper.getSearchHistoryListByScreen(userId, keyword);
        return searchHistoryListByScreen;
    }

    @Override
    public List<FeedbackGuo> getFeedbackListByScreen(Integer id, String username) {
        return feedbackMapper.getFeedbackListBySreen(id,username);
    }*/
}
