package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Groupon;
import com.cskaoyan.mall.bean.GrouponRules;
import com.cskaoyan.mall.vo.BaseRespVo;

public interface GrouponService {
    BaseRespVo selectAllGroupon(int page, int limit, GrouponRules grouponRules);

    BaseRespVo update(GrouponRules grouponRules);

    BaseRespVo delete(GrouponRules grouponRules);

    BaseRespVo create(GrouponRules grouponRules);

    BaseRespVo listRecord(int page, int limit, GrouponRules grouponRules);
}
