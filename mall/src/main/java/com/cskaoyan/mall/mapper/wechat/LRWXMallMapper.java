package com.cskaoyan.mall.mapper.wechat;

import com.cskaoyan.mall.bean.*;
import com.cskaoyan.mall.bean.wechat.UserCouponBean;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 李锐
 */
@Repository
public interface LRWXMallMapper {

    List<Category> queryCategoryList();

    Category queryFirstLevel1Category();

    List<Category> queryCurrentSubCategory(Integer pid);

    @Select("select count(*) from cskaoyan_mall_goods where deleted = false")
    Integer queryGoodsCount();

    Keyword queryDefaultKeyword();

    List<Keyword> queryHotKeywordList();

    @Select("select keyword from cskaoyan_mall_keyword where keyword like #{keyword} and deleted = false")
    List<String> querySearchHelper(@Param("keyword") String keyword);

    @Select("select keyword from cskaoyan_mall_search_history where user_id = #{userId} and deleted = false")
    List<Map> queryHistoryKeywordList(int userId);

    @Select("select id from cskaoyan_mall_user where username = #{username} and deleted = false")
    int queryUserIdByUserName(String username);

    @Update("update cskaoyan_mall_search_history set deleted = true where user_id = #{userId}")
    Integer deleteSearchHistory(int userId);

    @Select("select id from cskaoyan_mall_user where username = #{username} and deleted = false")
    int selectUserIdByUserName(String username);

    List<Order> queryOrdersByUserAndStatus(int userId, int status);

    //查询对应订单的评论状态 -1超期不可评论，0未评论，其他则对应评论表的id
    int queryCommentStatusByOrderId(Integer orderId);

    List<UserCouponBean> queryMyCouponListByStatus(int userId, int status);

    int queryCouponehaded(Integer couponId, int userId);


    @Select("select count(*) from cskaoyan_mall_coupon where id = #{couponId} and deleted = false")
    int queryCoupondeleted(Integer couponId);

    int addCouponForUser(int userId, Integer couponId, Date startTime, Date endTime, Date addTime);

    Coupon queryCouponById(Integer couponId);

    @Update("update cskaoyan_mall_coupon set deleted = true where id = #{couponId}")
    int deleteCouponById(Integer couponId);

    @Update("update cskaoyan_mall_coupon set total = #{total} where id = #{couponId}")
    int updateCouponTotal(Integer couponId, int total);

    @Select("select * from cskaoyan_mall_coupon where deleted = false")
    List<Coupon> queryAllCouponList();

    @Select("select count(*) from cskaoyan_mall_cart where user_id = #{userId} and deleted = false")
    Integer querGoodsCount(int userId);

    GoodsProduct queryGoodsProductById(Integer goodsId);

    int insertCart(Cart cart);

    Address queryDefaultAddressByUserId(int userId);

    Address queryAddressByAddressId(Integer addressId);

    Cart queryCartById(int cartId);

    List<Cart> queryCartByUserId(int userId);

    @Select("Select max(id) from cskaoyan_mall_cart where user_id = #{userId}")
    int queryCartId(int userId);
}
