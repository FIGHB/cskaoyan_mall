package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.mapper.*;
import com.cskaoyan.mall.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuoGoodsServiceImpl implements GuoGoodsService {

    @Autowired
    CommentMapper commentMapper;
    @Autowired
    CommentMapperGuo commentMapperGuo;
    @Autowired
    GoodsMapper goodsMapper;


    /*7777777777777777777777777777777777777777777777777777777777777777777*/
    @Autowired
    GoodsAttributeMapper goodsAttributeMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GuoCategoryMapper guoCategoryMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    GuoSpecificationMapper guoSpecificationMapper;
    @Autowired
    GuoBrandMapper guoBrandMapper;

    @Override
    public List<Comment> getCommentList(CommentShow commentShow) {
        CommentExample commentExample = new CommentExample();
        CommentExample.Criteria criteria = commentExample.createCriteria();
        if(commentShow.getUserId()!=null&&commentShow.getValueId()!=null){
            criteria.andUserIdEqualTo(commentShow.getUserId()).andValueIdEqualTo(commentShow.getValueId()).andDeletedEqualTo(false);
        }else if(commentShow.getUserId()==null&&commentShow.getValueId()!=null){
            criteria.andValueIdEqualTo(commentShow.getValueId()).andDeletedEqualTo(false);
        }else if(commentShow.getUserId()!=null&&commentShow.getValueId()==null){
            criteria.andUserIdEqualTo(commentShow.getUserId()).andDeletedEqualTo(false);
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
        List<Comment> commentList = commentMapper.selectByExample(commentExample);
        return commentList;
    }

    @Override
    public void deleteCommentById(Integer id) {
        commentMapperGuo.deleteCommentById(id);
    }





    /*888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888*/

    @Override
    public List<Goods> getGoodsList(GoodsShow goodsShow) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        if(goodsShow.getGoodsSn()!=null&&goodsShow.getName()!=null){
            criteria.andGoodsSnEqualTo(goodsShow.getGoodsSn()).andNameLike("%"+goodsShow.getName()+"%").andDeletedEqualTo(false);
        }else if(goodsShow.getGoodsSn()==null&&goodsShow.getName()!=null){
            criteria.andNameLike("%"+goodsShow.getName()+"%").andDeletedEqualTo(false);
        }else if(goodsShow.getGoodsSn()!=null&&goodsShow.getName()==null){
            criteria.andGoodsSnEqualTo(goodsShow.getGoodsSn()).andDeletedEqualTo(false);
        }else {
            criteria.andIdIsNotNull().andDeletedEqualTo(false);
        }
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
    }

    @Override
    public GoodsDetail getGoodsDetailByGoodsId(Integer id) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andIdEqualTo(id).andDeletedEqualTo(false);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        Goods goods=goodsList.get(0);
        GoodsDetail goodsDetail=new GoodsDetail();
        goodsDetail.setGoods(goods);//setGoods

        GoodsAttributeExample goodsAttributeExample = new GoodsAttributeExample();
        GoodsAttributeExample.Criteria criteria1 = goodsAttributeExample.createCriteria();
        criteria1.andGoodsIdEqualTo(id).andDeletedEqualTo(false);
        List<GoodsAttribute> goodsAttributes = goodsAttributeMapper.selectByExample(goodsAttributeExample);
        goodsDetail.setAttributes(goodsAttributes);//setGoodsAttributes

        List<GoodsSpecification> goodsSpecificationList=guoSpecificationMapper.getSpecificationByGoodsId(id);
        goodsDetail.setSpecifications(goodsSpecificationList);

        GoodsProductExample goodsProductExample = new GoodsProductExample();
        GoodsProductExample.Criteria criteria3 = goodsProductExample.createCriteria();
        criteria3.andGoodsIdEqualTo(id).andDeletedEqualTo(false);
        List<GoodsProduct> goodsProducts = goodsProductMapper.selectByExample(goodsProductExample);
        goodsDetail.setProducts(goodsProducts);

        Integer categoryId1 = goods.getCategoryId();
        int categoryId2 = guoCategoryMapper.getCategoryPidByCategoryId(categoryId1);
        int[] categorys = new int[2];
        categorys[0]=categoryId1;
        categorys[1]=categoryId2;
        goodsDetail.setCategoryIds(categorys);

        return goodsDetail;
    }

    @Override
    public Categorys getCategorys() {
        List<Category> categoriesParents=guoCategoryMapper.getCategorys(0);
        List<Category> categoriesChildren=guoCategoryMapper.getCategorysPidNot0();
        Categorys categorys = new Categorys();
        List<CategoryGuo> categoryGuoList=new ArrayList<>();

        for (Category categoriesParent : categoriesParents) {
            CategoryGuo categoryGuo = new CategoryGuo();
            categoryGuo.setValue(categoriesParent.getId());
            categoryGuo.setLabel(categoriesParent.getName());
            List<CategoryGuo> categoryGuos=new ArrayList<>();
            for (Category categoriesChild : categoriesChildren) {
                int pid = categoriesChild.getPid();
                int id = categoriesParent.getId();
                if(pid==id){
                    CategoryGuo categoryGuo1 = new CategoryGuo();
                    categoryGuo1.setValue(categoriesChild.getId());
                    categoryGuo1.setLabel(categoriesChild.getName());
                    categoryGuos.add(categoryGuo1);
                }
            }
            categoryGuo.setChildren(categoryGuos);
            categoryGuoList.add(categoryGuo);
        }
        categorys.setCategoryList(categoryGuoList);

        //setbrandList
        List<Brand> brands= guoBrandMapper.getBrandList();
        List<BrandShow> brandList=new ArrayList<>();
        for (Brand brand : brands) {
            BrandShow brandShow = new BrandShow();
            brandShow.setValue(brand.getId());
            brandShow.setLabel(brand.getName());
            brandList.add(brandShow);
        }
        categorys.setBrandList(brandList);
        return categorys;
    }

    @Override
    public Boolean updateDetails(GoodsDetail goodsDetail) {
        //更新goods
        Goods goods = goodsDetail.getGoods();
        return true;
    }

}
