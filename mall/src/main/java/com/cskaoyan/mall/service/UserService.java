package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.vo.*;
import org.springframework.stereotype.Service;

import java.util.List;

/*国旭的*/
@Service
public interface UserService {

    List<User> getUserList();
    List<Address> getAddressList(AdressShow adressShow);
    List<Collect> getConlectList(CollectShow collectShow);
    List<Footprint> getFootPrintList(FootprintShow footprintShow);
    List<SearchHistory> getSearchHistoryList(SearchHistoryShow searchHistoryShow);
    List<Feedback> getFeedbackList(FeedbackShow feedbackShow);
    List<User> getUserListByScreen(String mobile, String username);
    /*List<Collect> getCollectListByScreen(Integer valueId, Integer userId);
    List<Footprint> getFootprintByScreen(Integer goodsId, Integer userId);
    List<SearchHistoryGuo> getSearchHistoryByScreen(Integer userId, String keyword);
    List<FeedbackGuo> getFeedbackListByScreen(Integer id, String username);*/
}
