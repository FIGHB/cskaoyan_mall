package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.vo.FeedbackGuo;
import com.cskaoyan.mall.vo.SearchHistoryGuo;
import org.springframework.stereotype.Service;

import java.util.List;

/*国旭的*/
@Service
public interface UserService {

    List<User> getUserList();
    List<Address> getAddressList();
    List<Collect> getConlectList();
    List<Footprint> getFootPrintList();
    List<SearchHistoryGuo> getSearchHistory();
    List<FeedbackGuo> getFeedbackList();
    List<User> getUserListByScreen(String mobile, String username);
    List<Address> getAddressListByScreen(Integer userId, String name);
    List<Collect> getCollectListByScreen(Integer valueId, Integer userId);
    List<Footprint> getFootprintByScreen(Integer goodsId, Integer userId);
    List<SearchHistoryGuo> getSearchHistoryByScreen(Integer userId, String keyword);
    List<FeedbackGuo> getFeedbackListByScreen(Integer id, String username);
}
