package com.cskaoyan.mall.mapper.WXMapper;
import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.vo.GrouponDataVO;
import com.cskaoyan.mall.vo.MyGrouponDataVO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GrouponListMapper {

    List<GrouponDataVO> queryGrouponRuleList() ;

    Goods queryGoodsById(int goodsId);

    int queryGrouponListCount();

    int queryUserIdByName(String username);

    List<MyGrouponDataVO> queryGrouponListByCreatUserId(int userId);

    List<MyGrouponDataVO> queryGrouponListByUserId(int userId);

    Groupon queryGrouponById(int id);

    GrouponRules queryRuleById(int rulesId);

    List<OrderGoods> queryGoodsByOrderId(int orderId);

    String queryOrderSnByOrderId(int orderId);

    int queryOrderStatusByOrderId(int orderId);

    int queryJoinerCountByGrouponId(int grouponId);

    String queryCeatorNameByCreatUserId(int creatorUserId);

    Order queryOrderByOrderId(int orderId);
}
