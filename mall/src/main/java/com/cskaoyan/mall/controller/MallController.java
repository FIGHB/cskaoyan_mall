package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Brand;
import com.cskaoyan.mall.bean.Category;
import com.cskaoyan.mall.bean.Issue;
import com.cskaoyan.mall.bean.Keyword;
import com.cskaoyan.mall.service.MallService;
import com.cskaoyan.mall.utils.ListBean;
import com.cskaoyan.mall.vo.BaseRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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

    /**
     * 周榆淮
     * 订单管理查看列表
     */
    @RequestMapping("/order/list")
    public BaseRespVo queryOrderList(int page,int limit,String sort,String order,Integer userId,String orderSn,Short[] orderStatusArray){
        return BaseRespVo.ok(mallService.queryOrderList(page, limit, sort, order,userId,orderSn,orderStatusArray));
    }

    /**
     * 周榆淮
     * 查看订单详情
     */
    @RequestMapping("/order/detail")
    public BaseRespVo queryOrderDetail(int id){
        return BaseRespVo.ok(mallService.queryOrderDetail(id));
    }

    /**
     * 周榆淮
     * 通用问题查看列表
     */
    @RequestMapping("/issue/list")
    public BaseRespVo queryIssueList(int page,int limit,String sort,String order,String question){
        return BaseRespVo.ok(mallService.queryIssueList(page, limit, sort, order, question));
    }

    /**
     * 周榆淮
     * 插入通用问题
     */
    @RequestMapping("/issue/create")
    public BaseRespVo insertIssueList(@RequestBody Issue issue){
        Issue result = mallService.insertIssueList(issue);
        return BaseRespVo.ok(result);
    }

    /**
     * 周榆淮
     * 通用问题修改
     */
    @RequestMapping("/issue/update")
    public BaseRespVo updateIssue(@RequestBody Issue issue){
        Issue result = mallService.updateIssue(issue);
        return BaseRespVo.ok(result);
    }

    /**
     * 周榆淮
     * 通用问题删除
     */
    @RequestMapping("/issue/delete")
    public BaseRespVo deleteIssue(@RequestBody Issue issue){
        mallService.deleteIssue(issue.getId());
        return BaseRespVo.ok(null);
    }

    /**
     * 周榆淮
     * 关键词查看列表
     */
    @RequestMapping("/keyword/list")
    public BaseRespVo queryKeyWordList(int page,int limit,String sort,String order,String keyword, String url){
        return BaseRespVo.ok(mallService.queryKeyWordList(page, limit, sort, order, keyword, url));
    }

    /**
     * 周榆淮
     * 插入关键词
     */
    @RequestMapping("/keyword/create")
    public BaseRespVo insertKeyWordList(@RequestBody Keyword keyword){
        Keyword result = mallService.insertKeyWordList(keyword);
        return BaseRespVo.ok(result);
    }
    /**
     * 周榆淮
     * 修改关键词
     */
    @RequestMapping("/keyword/update")
    public BaseRespVo updateKeyWord(@RequestBody Keyword keyword){
        Keyword result = mallService.updateKeyWord(keyword);
        return BaseRespVo.ok(result);
    }

    /**
     * 周榆淮
     * 关键词删除
     */
    @RequestMapping("/keyword/delete")
    public BaseRespVo deleteKeyWord(@RequestBody Keyword keyword){
        mallService.deleteKeyWord(keyword.getId());
        return BaseRespVo.ok(null);
    }

    /**
     * 李锐
     * 商品类目查看
     * @return
     */
    @RequestMapping("/category/list")
    public BaseRespVo getCategoryList() {
        List<Map<String, Object>> data = mallService.getCategoryList();
        return BaseRespVo.ok(data);
    }
    @RequestMapping("/category/l1")
    public BaseRespVo getSimpaleCategoryList() {
        List<Category> data = mallService.getSimpleCategoryList();
        return BaseRespVo.ok(data);
    }
}
