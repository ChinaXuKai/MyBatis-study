package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.SelectMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author xukai
 * @create 2022-08-07 13:46
 */
public class SelectMapperTest {

    /**
     * mybatis 的各种查询功能：
     * 1、 若查询出的数据只有一条
     * a>可以通过实体类对象接收
     * b>可以通过 List 集合来接收
     * b>可以通过 Map 集合来接收 ：以字段为键，以字段所对应的值为值
     *          例如结果：{password=qwer, sex=男, id=3, age=20, email=xukai@qq.com, username=xukai}
     *
     * 2、 若查询出来的数据有多条：
     * a>可以通过 实体类类型 的 List 集合来接收，
     * b>可以通过 map类型 的 List 集合来接收，
     *              但一定不能通过实体类对象接收，此时会抛异常：TooManyResultsException
     * c>可以在 mapper 接口的方法上添加 @MapKey 注解，此时就可以将 每条数据转换的map集合作为值，以查询结果的某个字段作为键
     *              放在同一个map集合中
     *
     *
     * mybatis 中设置了默认的类型别名
     * java.lang.Integer ---> int,integer
     * int ---> _int,_integer
     * Map ---> map
     * String ---> string
     */
    @Test
    public void testGetUserById(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> users = mapper.getUserById(3);
        System.out.println(users);
    }
    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        List<User> users = mapper.getAllUser();
        System.out.println(users);
    }
    @Test
    public void testGetCount(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Integer count = mapper.getCount();
        System.out.println(count);
    }
    @Test
    public void testGetUserByIdToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> user = mapper.getUserByIdToMap(3);
        System.out.println(user);
    }
    @Test
    public void testGetAllUserToMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        SelectMapper mapper = sqlSession.getMapper(SelectMapper.class);
        Map<String, Object> userMap = mapper.getAllUserToMap();
        System.out.println(userMap);
    }

}
