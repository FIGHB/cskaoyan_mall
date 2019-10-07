package com.cskaoyan.mall.mapper.wechat;

import com.cskaoyan.mall.bean.Address;
import com.cskaoyan.mall.bean.wxfbean.AddressDetailWx;
import com.cskaoyan.mall.bean.wxfbean.AddressSimpleWx;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 赵亚云
 */
@Repository
public interface AddressWxMapper {

    //展示所有的地址信息
    @Select("select is_default as isDefault,address as detailedAddress,name,mobile,id from cskaoyan_mall_address")
    List<AddressSimpleWx> queryAllAddress();

 //展示单个地址的详细信息，根据id查询
    @Select("select id,name,province_id as provinceId,city_id as cityId,area_id as areaId,address,mobile,is_default as isDefault from cskaoyan_mall_address where id = #{id}")
    AddressDetailWx queryAddressDetail(@Param("id") int id);
    //查询地址编号对应的地址名字
    @Select("select name from cskaoyan_mall_region where id = #{id}")
    String queryRegionNameById(@Param("id") int regionId);

    //插入数据
    /*@Insert("insert into cskaoyan_mall_address (id,name,province_id,city_id,area_id,address,mobile,is_default) " +
            " values (null,#{name},#{provinceId},#{cityId},#{areaId},#{address},#{mobile},#{isDefault})")
    int insertAddress(@Param("id") int id,@Param("name") String name,@Param("provinceId") int provinceId, @Param("cityId") int cityId,@Param("areaId") int areaId,@Param("address") String address,@Param("mobile") String mobile,@Param("isDefault") int isDefault);
    //查询数据插入到了第几行*/
    /*@Select("select last_insert_id() as id from cskaoyan_mall_address")*/

    Address selectLastId();
    /*@Select("select id from cskaoyan_mall_address where name = #{name},province_id= #{provinceId},city_id = #{cityId},area_id = #{areaId},address = #{address},mobile = #{mobile},is_default = #{isDefault}")
    int selectIdByInsert(@Param("id") int id,@Param("name") String name,@Param("provinceId") int provinceId, @Param("cityId") int cityId,@Param("areaId") int areaId,@Param("address") String address,@Param("mobile") String mobile,@Param("isDefault") int isDefault);*/
}
