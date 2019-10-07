package com.cskaoyan.mall.service.WXService;

import com.cskaoyan.mall.bean.Goods;
import com.cskaoyan.mall.bean.Order;
import com.cskaoyan.mall.mapper.WXMapper.GrouponListMapper;
import com.cskaoyan.mall.vo.GrouponDataVO;
import com.cskaoyan.mall.vo.GrouponListVO;
import com.cskaoyan.mall.vo.MyGrouponDataVO;
import com.cskaoyan.mall.vo.MyGrouponVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GrouponServiceListImpl implements GrouponListService {
    @Autowired
    GrouponListMapper grouponMapper;

    @Override
    public GrouponListVO queryGrouponList(int page, int size) {
        PageHelper.startPage(page,size);
        List<GrouponDataVO> grouponDataList = grouponMapper.queryGrouponRuleList();
        for (GrouponDataVO grouponData : grouponDataList){
            Goods good = grouponMapper.queryGoodsById(grouponData.getGoodsId());
            grouponData.setGoods(good);
            grouponData.setGroupon_price(good.getRetailPrice().subtract(grouponData.getDiscount()));
        }
        GrouponListVO grouponListVO = new GrouponListVO();
        grouponListVO.setData(grouponDataList);
        grouponListVO.setCount(grouponMapper.queryGrouponListCount());
        return grouponListVO;
    }

    @Override
    public MyGrouponVO queryMyGroupOn(int showType,String username) {
        HashMap<Integer, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put(101, "未付款");
        stringStringHashMap.put(102, "用户取消");
        stringStringHashMap.put(103, "系统取消");
        stringStringHashMap.put(201, "已付款");
        stringStringHashMap.put(202, "申请付款");
        stringStringHashMap.put(203, "已退款");
        stringStringHashMap.put(301, "已发货");
        stringStringHashMap.put(401, "用户发货");
        stringStringHashMap.put(402, "系统发货");
        MyGrouponVO myGrouponVO = new MyGrouponVO();
        Map handleOption = new HashMap();
        handleOption.put("cancel",false);
        handleOption.put("comment",false);
        handleOption.put("confirm",false);
        handleOption.put("delete",true);
        handleOption.put("pay",false);
        handleOption.put("rebuy",false);
        handleOption.put("refund",false);
        int userId = grouponMapper.queryUserIdByName(username);
        //我发起的团购
        if (showType == 0){
            List<MyGrouponDataVO> grouponList = grouponMapper.queryGrouponListByCreatUserId(userId);
            myGrouponVO.setCount(grouponList.size());
            if (grouponList != null){
                for (MyGrouponDataVO myGrouponData : grouponList){
                    myGrouponData.setHandleOption(handleOption);
                    myGrouponData.setGroupon(grouponMapper.queryGrouponById(myGrouponData.getId()));
                    myGrouponData.setRules(grouponMapper.queryRuleById(myGrouponData.getRulesId()));
                    myGrouponData.setGoodsList(grouponMapper.queryGoodsByOrderId(myGrouponData.getOrderId()));
                    myGrouponData.setIsCreator(true);
                    myGrouponData.setOrderSn(grouponMapper.queryOrderSnByOrderId(myGrouponData.getOrderId()));
                    myGrouponData.setOrderStatusText(stringStringHashMap.get(grouponMapper.queryOrderStatusByOrderId(myGrouponData.getOrderId())));
                    myGrouponData.setJoinerCount(grouponMapper.queryJoinerCountByGrouponId(myGrouponData.getGrouponId()));
                    myGrouponData.setCreator(grouponMapper.queryCeatorNameByCreatUserId(myGrouponData.getCreatorUserId()));
                    Order order = grouponMapper.queryOrderByOrderId(myGrouponData.getOrderId());
                    myGrouponData.setActualPrice(order.getGoodsPrice().subtract(order.getGrouponPrice()));
                }
                myGrouponVO.setData(grouponList);
            }else{
                myGrouponVO.setCount(0);
                myGrouponVO.setData(null);
                return myGrouponVO;
            }
        }
        if (showType == 1){
            List<MyGrouponDataVO> grouponList = grouponMapper.queryGrouponListByUserId(userId);
            myGrouponVO.setCount(grouponList.size());
            if (grouponList != null){
                for (MyGrouponDataVO myGrouponData : grouponList){
                    myGrouponData.setHandleOption(handleOption);
                    myGrouponData.setGroupon(grouponMapper.queryGrouponById(myGrouponData.getId()));
                    myGrouponData.setRules(grouponMapper.queryRuleById(myGrouponData.getRulesId()));
                    myGrouponData.setGoodsList(grouponMapper.queryGoodsByOrderId(myGrouponData.getOrderId()));
                    myGrouponData.setIsCreator(false);
                    myGrouponData.setOrderSn(grouponMapper.queryOrderSnByOrderId(myGrouponData.getOrderId()));
                    myGrouponData.setOrderStatusText(stringStringHashMap.get(grouponMapper.queryOrderStatusByOrderId(myGrouponData.getOrderId())));
                    myGrouponData.setJoinerCount(grouponMapper.queryJoinerCountByGrouponId(myGrouponData.getGrouponId()));
                    myGrouponData.setCreator(grouponMapper.queryCeatorNameByCreatUserId(myGrouponData.getCreatorUserId()));
                    Order order = grouponMapper.queryOrderByOrderId(myGrouponData.getOrderId());
                    myGrouponData.setActualPrice(order.getGoodsPrice().subtract(order.getGrouponPrice()));
                }
                myGrouponVO.setData(grouponList);
            }else{
                myGrouponVO.setCount(0);
                myGrouponVO.setData(null);
                return myGrouponVO;
            }
        }
        return  myGrouponVO;
    }
}
