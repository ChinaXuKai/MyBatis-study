package com.atguigu.mybatis.mapper;


import com.atguigu.mybatis.pojo.User;

import java.util.List;

/**
 * mybatis 面向接口编程的两个一致：
 * 1. 映射文件的namespace 要和 mapper 接口的全类名保持一致
 * 2. 映射文件的SQL语句的id 要和 mapper 接口中的方法名保持一致
 *
 *
 * 映射关系：表---实体类---mapper接口---映射文件
 */
public interface UserMapper {

    /**
     * 添加用户信息
     * @return 影响的行数
     */
    int insertUser();


    /**
     * 修改用户信息
     * @return 影响的行数
     */
    int updateUser();


    /**
     * 删除用户信息
     * @return 影响的行数
     */
    int deleteUser();


    /**
     * 查询用户信息
     * @return 返回查询到的用户
     */
    User getUserById();


    /**
     * 查询所有的用户信息
     * @return 返回查询到的所有用户集合
     */
    List<User> getAllUser();
}
