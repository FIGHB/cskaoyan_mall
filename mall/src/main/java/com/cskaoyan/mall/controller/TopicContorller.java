package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.StringTopic;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.TopicService;
import com.cskaoyan.mall.utils.IsNumber;
import com.cskaoyan.mall.utils.SetTopic;
import com.cskaoyan.mall.vo.BaseRespObject;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
public class TopicContorller {
    @Autowired
    TopicService topicService;

    @RequestMapping("/admin/topic/list")
    @RequiresPermissions("admin:topic:list")
    public BaseRespVo topicList(int page,int limit,Topic topic){
        BaseRespVo baseRespVo = topicService.selectAllTopic(page,limit,topic);
        return baseRespVo;
    }

    @RequestMapping("/admin/topic/create")
    @RequiresPermissions("admin:topic:create")
    public BaseRespVo create(@RequestBody StringTopic stringTopic){
        Topic topic = new Topic();
        String price = stringTopic.getPrice();
        if(price!=null&&price!=""){
            if(price.length()>10){
                return BaseRespVo.fail(401, "参数不对");
            }
            if(IsNumber.isNumber(price)) {
                int i = Integer.parseInt(price);
                BigDecimal bigDecimal = new BigDecimal(i);
                topic.setPrice(bigDecimal);
            }else {
                return BaseRespVo.fail(401, "参数不对");
            }
        }
        topic = SetTopic.setTopic(topic,stringTopic);
        BaseRespVo insert = topicService.insert(topic);
        return insert;
    }

    @RequestMapping("/admin/topic/delete")
    @RequiresPermissions("admin:topic:delete")
    public BaseRespVo delete(@RequestBody  Topic topic){
        BaseRespVo delete = topicService.delete(topic);
        return delete;
    }

    @RequestMapping("/admin/topic/update")
    @RequiresPermissions("admin:topic:update")
    public BaseRespVo update(@RequestBody StringTopic stringTopic){
        Topic topic = new Topic();
        String price = stringTopic.getPrice();
        if(price!=null&&price!=""){
            if(price.length()>10){
                return BaseRespVo.fail(401, "参数不对");
            }
            if(IsNumber.isNumber(price)) {
                int i = Integer.parseInt(price);
                BigDecimal bigDecimal = new BigDecimal(i);
                topic.setPrice(bigDecimal);
            }else {
                return BaseRespVo.fail(401, "参数不对");
            }
        }
        topic = SetTopic.setTopic(topic,stringTopic);
        BaseRespVo update = topicService.update(topic);
        return update;
    }
}
