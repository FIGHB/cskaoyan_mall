package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.service.MallService;
import com.cskaoyan.mall.utils.ListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("admin")
public class MallController {
    @Autowired
    MallService mallService;
    /**
     * 周榆淮
     * 查询行政规划
     */
    @RequestMapping("region/list")
    public BaseRespVo queryRegionList(){
        return BaseRespVo.ok(mallService.queryRegionList());
    }

    /**
     * 周榆淮
     * 品牌制造商显示
     */
    @RequestMapping("/brand/list")
    public BaseRespVo queryBrandList(int page,int limit,String sort,String order,Integer id,String name){
        return BaseRespVo.ok(mallService.queryBrandList(page, limit, sort, order,id,name));
    }

    /**
     * 周榆淮
     * 品牌制造商添加
     */
    @RequestMapping("/brand/create")
    public BaseRespVo insertBrandList(@RequestBody Brand brand){
        Brand result = mallService.insertBrandList(brand);
      return BaseRespVo.ok(result);
    }

    /**
     * 周榆淮
     * 品牌制造商修改
     */
    @RequestMapping("/brand/update")
    public BaseRespVo updateBrand(@RequestBody Brand brand){
        Brand result = mallService.updateBrand(brand);
        return BaseRespVo.ok(result);
    }

    /**
     * 周榆淮
     * 品牌制造商删除
     */
    @RequestMapping("/brand/delete")
    public BaseRespVo deleteBrand(@RequestBody Brand brand){
        mallService.deleteBrand(brand.getId());
        return BaseRespVo.ok(null);
    }

}
