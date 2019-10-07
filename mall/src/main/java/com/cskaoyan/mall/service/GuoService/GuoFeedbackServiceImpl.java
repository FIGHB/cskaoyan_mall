package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.bean.FeedbackExample;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.FeedbackMapper;
import com.cskaoyan.mall.mapper.GuoMapper.GuoFeedbackMapper;
import com.cskaoyan.mall.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuoFeedbackServiceImpl implements GuoFeedbackService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    FeedbackMapper feedbackMapper;

    @Override
    public void feedbackByUsername(String username, Feedback feedback) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<User> users = userMapper.selectByExample(userExample);
        User user = users.get(0);
        feedback.setUsername(username);
        feedback.setUserId(user.getId());
        //这个status不知道干啥的
        feedback.setStatus(1);

        int insert = feedbackMapper.insert(feedback);
    }
}
