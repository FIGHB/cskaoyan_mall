package com.cskaoyan.mall.mapper.wechat;

import com.cskaoyan.mall.bean.Ad;
import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.wxfbean.AddressDetailWx;
import com.cskaoyan.mall.bean.wxfbean.AddressSimpleWx;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 赵亚云
 */
@Repository
public interface AddressWxMapper {

    //展示所有的地址信息，封装在AddressSimpleWx中
    @Select("select is_default as isDefault,address as detailedAddress,name,mobile,id from cskaoyan_mall_address where user_id = #{userId}")
    List<AddressSimpleWx> queryAllAddressSimple(int userId);

 //展示单个地址的详细信息，根据id查询
    @Select("select id,name,province_id as provinceId,city_id as cityId,area_id as areaId,address,mobile,is_default as isDefault from cskaoyan_mall_address where id = #{id}")
    AddressDetailWx queryAddressDetail(@Param("id") int id);
    //查询地址编号对应的地址名字
    @Select("select name from cskaoyan_mall_region where id = #{id}")
    String queryRegionNameById(@Param("id") int regionId);
    //最后一次插入数据的位置
    Address selectLastId();
    //设置指定地址是否为默认地址
    int updateIsDefault(@Param("id") int id,boolean isDefault);


    //查询所有的地址信息，封装在Address中
    List<Address> queryAllAddress(int userId);
    //修改地址信息
    int updateAddress(Address address);

    //查询登陆用户的id
    @Select("select id from cskaoyan_mall_user where username = #{username}")
    int queryUserId(@Param("username") String username);

    int insertAddress(Address address);

    @Select("select id from cskaoyan_mall_address where add_time = #{addTime} limit 0, 1")
   int getIdByaddTime(Date date);
}
