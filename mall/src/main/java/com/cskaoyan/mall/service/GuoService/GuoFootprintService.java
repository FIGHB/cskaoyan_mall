package com.cskaoyan.mall.service.GuoService;

import com.cskaoyan.mall.bean.Footprint;
import com.cskaoyan.mall.vo.GuoVo.FootprintDetail;

import java.util.List;

public interface GuoFootprintService {
    List<Footprint> getFootprintList();

    List<FootprintDetail> getFootprintDetailList(List<Footprint> footprintList);
}
