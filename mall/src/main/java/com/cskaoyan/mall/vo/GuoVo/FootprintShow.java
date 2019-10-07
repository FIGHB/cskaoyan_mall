package com.cskaoyan.mall.vo.GuoVo;

import com.cskaoyan.mall.bean.Footprint;

import java.util.List;

public class FootprintShow {
    List<FootprintDetail> footprintList;
    Long totalPages;

    public List<FootprintDetail> getFootprintList() {
        return footprintList;
    }

    public void setFootprintList(List<FootprintDetail> footprintList) {
        this.footprintList = footprintList;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "FootprintShow{" +
                "footprintList=" + footprintList +
                ", totalPages=" + totalPages +
                '}';
    }
}
