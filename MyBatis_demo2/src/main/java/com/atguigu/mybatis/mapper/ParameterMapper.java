package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author xukai
 * @create 2022-08-06 23:59
 */
public interface ParameterMapper {

    /**
     * 查询所有的员工信息
     * @return
     */
    List<User> getAllUser();


    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User getUserByUsername(String username);


    /**
     * 验证登录
     * @param username
     * @param password
     * @return
     */
    User checkLogin(String username, String password);


    /**
     * 验证登录
     * @return
     */
    User checkLoginByMap(Map<String,Object> map);


    /**
     * 添加用户
     * @return
     */
    int insertUser(User user);


    /**
     * 验证登录
     */
    User checkLoginByParam(@Param(value = "username") String username,@Param("password") String password);
}
