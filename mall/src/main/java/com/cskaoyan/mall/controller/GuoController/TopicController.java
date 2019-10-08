package com.cskaoyan.mall.controller.GuoController;

import com.cskaoyan.mall.bean.Feedback;
import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.bean.Region;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.service.GuoService.*;
import com.cskaoyan.mall.vo.BaseRespVo;
import com.cskaoyan.mall.vo.GuoVo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/wx")
public class TopicController {

    @Autowired
    GuoTopicService topicService;
    @Autowired
    GuoFeedbackService guoFeedbackService;
    @Autowired
    GuoFootprintService guoFootprintService;
    @Autowired
    GuoGroupService guoGroupService;
    @Autowired
    GuoOrderService guoOrderService;

    @RequestMapping("/topic/list")
    @ResponseBody
    public BaseRespVo getTopicList(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Topic> topicList = topicService.getTopicList();
        GuoTopicShow guoTopicShow = itemsList(topicList);
        BaseRespVo ok = BaseRespVo.ok(guoTopicShow);
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
        BaseRespVo ok = BaseRespVo.ok(topicList);
        return ok;
    }
    @RequestMapping("/region/list")
    @ResponseBody
    public BaseRespVo getRegionList(Integer pid){
        List<Region> regionList=topicService.getRegionList(pid);
        BaseRespVo ok = BaseRespVo.ok(regionList);
        return ok;
    }
    @RequestMapping("/feedback/submit")
    @ResponseBody
    public BaseRespVo feedbackByUsername(@RequestBody Feedback feedback) {
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();

        guoFeedbackService.feedbackByUsername(username,feedback);

        return nullDataBaseRespVo();
    }
    @RequestMapping("/footprint/list")
    @ResponseBody
    public BaseRespVo footprintShow(Integer page, Integer size){
        PageHelper.startPage(page,size);
        List<Footprint> footprintList=guoFootprintService.getFootprintList();
        FootprintShow footprintShow = itemsListF(footprintList,size);
        List<FootprintDetail> footprintDetailList=guoFootprintService.getFootprintDetailList(footprintList);
        footprintShow.setFootprintList(footprintDetailList);
        BaseRespVo ok = BaseRespVo.ok(footprintShow);
        return ok;
    }
    @RequestMapping("/groupon/detail")
    @ResponseBody
    public BaseRespVo getGroupDetail(Integer grouponId){
        GroupDetail groupDetailList=guoGroupService.getGroupDetailByGrouponId(grouponId);
        BaseRespVo ok = BaseRespVo.ok(groupDetailList);
        return ok;
    }
    @RequestMapping("/order/cancel")
    @ResponseBody
    public BaseRespVo CancelOrderById(@RequestBody CancelId cancelId ){
        guoOrderService.cancelOrderById(cancelId.getOrderId());
        return nullDataBaseRespVo();
    }


    public static BaseRespVo nullDataBaseRespVo(){
        BaseRespVo baseRespVo = new BaseRespVo();
        baseRespVo.setData(null);
        baseRespVo.setErrmsg("");
        baseRespVo.setErrno(0);
        return baseRespVo;
    }

    public static GuoTopicShow itemsList(List<Topic> list){
        PageInfo<Topic> pageInfo=new PageInfo<>(list);
        long total = pageInfo.getTotal();
        GuoTopicShow guoTopicShow=new GuoTopicShow();
        guoTopicShow.setData(list);
        guoTopicShow.setCount(total);
        return guoTopicShow;
    }
    public static FootprintShow itemsListF(List<Footprint> list, int size){
        PageInfo<Footprint> pageInfo=new PageInfo<>(list);
        long total = pageInfo.getTotal();
        FootprintShow guoTopicShow=new FootprintShow();
        if(total%size==0){
            guoTopicShow.setTotalPages(total/size);
        }else {
            guoTopicShow.setTotalPages(total/size+1);
        }
        return guoTopicShow;
    }

}
