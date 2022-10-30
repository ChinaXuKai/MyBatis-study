package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import javax.xml.soap.SAAJResult;
import java.util.List;
import java.util.Map;

/**
 * @author xukai
 * @create 2022-08-07 13:38
 */
public interface SelectMapper {

    /**
     * 根据用户id 查询用户
     */
    List<User> getUserById(@Param("id") Integer id);


    /**
     * 根据所有的用户信息
     */
    List<User> getAllUser();


    /**
     * 查询用户的总记录数
     */
    Integer getCount();


    /**
     * 根据id查询用户信息为一个map集合
     */
    Map<String,Object> getUserByIdToMap(@Param("id") Integer id);


    /**
     * 查询用户信息作为一个map集合
     */
//    List<Map<String, Object>> getAllUserToMap();
    @MapKey("id")
    Map<String, Object> getAllUserToMap();

}
