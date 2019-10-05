package com.cskaoyan.mall.controller.GuoController;

import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.controller.UserController;
import com.cskaoyan.mall.service.GuoService.TopicService;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.GuoVo.TopicDetail;
import com.cskaoyan.mall.vo.ItemsList;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wx")
public class TopicController {

    @Autowired
    TopicService topicService;

    @RequestMapping("/topic/list")
    @ResponseBody
    public BaseRespVo getTopicList(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Topic> topicList = topicService.getTopicList();
        ItemsList<Topic> topicItemsList = UserController.itemsList(topicList);
        BaseRespVo ok = BaseRespVo.ok(topicItemsList);
        return ok;
    }
    @RequestMapping("/topic/detail")
    @ResponseBody
    public BaseRespVo getTopicDetailById(Integer id){
        TopicDetail topicDetailList=topicService.getTopicDetailById(id);
        BaseRespVo ok = BaseRespVo.ok(topicDetailList);
        return ok;
    }
    @RequestMapping("/topic/related")
    @ResponseBody
    public BaseRespVo getTopicRelatedById(Integer id){
        int page=(int)(Math.random()*4)+1;
        PageHelper.startPage(page,4);
        List<Topic> topicList = topicService.getTopicList();
        ItemsList<Topic> topicItemsList = UserController.itemsList(topicList);
        BaseRespVo ok = BaseRespVo.ok(topicItemsList);
        return ok;
    }
    @RequestMapping("/region/list")
    @ResponseBody
    public BaseRespVo getRegionList(Integer pid){
        List<Region> regionList=topicService.getRegionList(pid);
        BaseRespVo ok = BaseRespVo.ok(regionList);
        return ok;
    }

}
