package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.ParameterMapper;
import com.atguigu.mybatis.pojo.User;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 注：属性并不是成员变量，真正的属性就是找到相对应的 get、set方法，把get、set方法中的get和set去掉，
         然后剩余部分小写，得到的就是属性名
 以后会遇到一种情况：没有相对应的成员变量，但有相对应的get、set方法，此时不能说没有这个属性
 */
public class ParameterMapperTest {

    @Test
    public void testGetAllUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        List<User> allUser = mapper.getAllUser();
        for (User user : allUser){
            System.out.println(user);
        }
    }


    /**
     * mybatis 获取参数值的两种方式：${} 和 #{}
     * ${} 本质是字符串拼接
     * #{} 本质是占位符赋值
     *
     * mybatis获取参数值的各种情况：
     * 1、mapper 接口方法的参数为单个的字面量类型
     *      可以通过 ${} 和 #{} 以任意的获取参数值，但需要注意 ${} 的单引号问题
     * 2、mapper 接口方法的参数为多个的字面量类型
     *      此时mybatis默认会将这些参数放在 一个map 集合中，以两种方式进行存储
     *      a>  以arg0、arg1为键，以参数为值
     *      b>  以param1、param2为键，以参数为值
     *      因此只需通过 #{} 和 ${} 以键的方式访问值即可，但是需要注意 ${} 的单引号问题
     * 3、若 mapper 接口方法的参数为多个时，可以手动将这些参数放在一个map中存储，这时mybatis的默认行为会取消
     *      只需要通过#{} 和 ${} 以键的方式访问值即可，但是需要注意 ${} 的单引号问题
     * 4、mapper 接口方法的参数是实体类类型的参数
     *      只需要通过#{} 和 ${} 以 属性 的方式访问 属性值 即可，但是需要注意 ${} 的单引号问题
     * 5、使用 @Param 注解来命名参数
     *      此时mybatis默认会将这些参数放在 一个map 集合中，以两种方式进行存储
     *      a>  以 @Param注解的值 为键，以参数为值
     *      b>  以param1、param2为键，以参数为值
     *      因此只需通过 #{} 和 ${} 以键的方式访问值即可，但是需要注意 ${}  的单引号问题
     *
     * 以上五种情况可以归结为两种情况：实体类类型的参数 和 @Param注解命名的参数
     *
     */
    @Test
    public void testGetUserByUsername(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.getUserByUsername("666");
        System.out.println(user);
    }
    @Test
    public void testCheckLogin(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLogin("666","qwer");
        System.out.println(user);
    }
    @Test
    public void testCheckLoginByMap(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        Map<String, Object> map = new HashMap<>();
        map.put("username", "666");
        map.put("password", "qwer");
        User user = mapper.checkLoginByMap(map );
        System.out.println(user);
    }
    @Test
    public void testInsertUser(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = new User(null,"ccc","qwer",20,"男","ccc@qq.com");
        int result = mapper.insertUser(user);
        System.out.println(result);
    }
    @Test
    public void testCheckLoginByParam(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        ParameterMapper mapper = sqlSession.getMapper(ParameterMapper.class);
        User user = mapper.checkLoginByParam("666", "qwer");
        System.out.println(user);
    }

}
