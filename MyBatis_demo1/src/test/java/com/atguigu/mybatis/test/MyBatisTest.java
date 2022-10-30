package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.UserMapper;
import com.atguigu.mybatis.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.List;

/**
 * @author
 * @create 2022-08-06 17:17
 */
public class MyBatisTest {


    /**
     * 一、SqlSession
     * 1、
     * SqlSession：代表Java程序和数据库之间的会话，可以用来操作数据库（HttpSession是Java程序和浏览器之间的会话）
     * SqlSessionFactory：是“生产”SqlSession的“工厂”。
     * 工厂模式：如果创建某一个对象，使用的过程基本固定，那么我们就可以把创建这个对象的
     * 相关代码封装到一个“工厂类”中，以后都使用这个工厂类来“生产”我们需要的对象。
     *
     * 2、
     * sqlSession.getMapper() ：底层是代理模式，可以帮我们返回一个  接口对应的实现类（底层创建的） 的对象
     *
     * 3、
     * SqlSession 默认不自动提交事务
     *      自动提交事务：openSession(boolean autoCommit) ---> openSession(true)
     * @throws IOException
     */
    @Test
    public void testMyBatis() throws IOException {
        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        //获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        //通过 sqlSessionFactoryBuilder 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //通过 sqlSessionFactory.openSession() 获取 SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        //通过 sqlSession.getMapper() 获取 mapper 接口对象
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //测试功能
        int result = mapper.insertUser();
        System.out.println("result: " + result);
        //提交事务
//        sqlSession.commit();
    }


    @Test
    public void testUpdateUser() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //测试功能
        int result = mapper.updateUser();
        System.out.println(result);
    }


    @Test
    public void testDeleteUserAndGetUserById() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSession sqlSession = new SqlSessionFactoryBuilder().build(is).openSession(true);
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //测试删除功能
//        int result = mapper.deleteUser();
//        System.out.println(result);
        //测试查询功能
//        User user = mapper.getUserById();
//        System.out.println(user);
        //测试查询多行查询
        List<User> userList = mapper.getAllUser();
        for (User user : userList){
            System.out.println(user);
        }
    }



}






