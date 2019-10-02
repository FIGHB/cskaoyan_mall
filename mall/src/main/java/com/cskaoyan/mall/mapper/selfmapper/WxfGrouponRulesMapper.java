package com.cskaoyan.mall.mapper.selfmapper;

import com.cskaoyan.mall.bean.CouponUser;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.bean.GrouponRulesExample;
import com.cskaoyan.mall.bean.Topic;
import com.cskaoyan.mall.vo.GrouponVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WxfGrouponRulesMapper {

    GrouponRules[] queryGrouponByCondition(GrouponRules grouponRules);

    long queryTotal(GrouponRules grouponRules);

    GrouponRules selectLastInsert();

    GrouponVo[] queryGrouponVo(GrouponRules grouponRules);
}
