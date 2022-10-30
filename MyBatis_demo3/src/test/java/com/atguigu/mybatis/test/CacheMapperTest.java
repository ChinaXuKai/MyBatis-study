package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.CacheMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author xukai
 * @create 2022-08-09 11:15
 */
public class CacheMapperTest {

    /**
     * 一级缓存是SqlSession级别的，通过同一个SqlSession查询的数据会被缓存，下次查询相同的数据，就
     * 会从缓存中直接获取，不会从数据库重新访问
     * 使一级缓存失效的四种情况：
         * 1) 不同的SqlSession对应不同的一级缓存
         * 2) 同一个SqlSession但是查询条件不同
         * 3) 同一个SqlSession两次查询期间执行了任何一次增删改操作
         * 4) 同一个SqlSession两次查询期间手动清空了缓存
     *
     * 二级缓存是SqlSessionFactory级别，通过同一个SqlSessionFactory创建的SqlSession查询的结果会被
     * 缓存；此后若再次执行相同的查询语句，结果就会从缓存中获取
     * 二级缓存开启的条件：
         * a>在核心配置文件中，设置全局配置属性cacheEnabled="true"，默认为true，不需要设置
         * b>在映射文件中设置标签<cache />
         * c>二级缓存必须在SqlSession关闭或提交之后有效
         * d>查询的数据所转换的实体类类型必须实现序列化的接口
     * 使二级缓存失效的情况：
         * 两次查询之间执行了任意的增删改，会使一级和二级缓存同时失效
     *
     *
     * 注意：在没有关闭sqlSession或没有提交sqlSession，所查询的数据会被保存到一级缓存中
     *              只有关闭sqlSession或没有提交sqlSession，所查询的数据才会被保存到二级缓存中
     */

    /**
     MyBatis缓存查询的顺序
         先查询二级缓存，因为二级缓存中可能会有其他程序已经查出来的数据，可以拿来直接使用。
         如果二级缓存没有命中，再查询一级缓存
         如果一级缓存也没有命中，则查询数据库
         SqlSession关闭之后，一级缓存中的数据会写入二级缓存
     */

    @Test
    public void testOneCache(){
        SqlSession sqlSession1 = SqlSessionUtils.getSqlSession();
        CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp1 = mapper1.getEmpByEid(1);
        System.out.println(emp1);
//        SqlSession sqlSession2 = SqlSessionUtils.getSqlSession();
//        CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
        CacheMapper mapper2 = sqlSession1.getMapper(CacheMapper.class);
        Emp emp2 = mapper2.getEmpByEid(1);
        System.out.println(emp2);
    }
    @Test
    public void testInsertEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        CacheMapper mapper = sqlSession.getMapper(CacheMapper.class);
        Emp emp1 = mapper.getEmpByEid(1);
        System.out.println(emp1);

//        mapper.insertEmp(new Emp(null, "xukai", 20, "男", "xukai@qq.com"));
        sqlSession.clearCache();

        Emp emp2 = mapper.getEmpByEid(1);
        System.out.println(emp2);
    }


    @Test
    public void testTwoCache(){
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

            SqlSession sqlSession1 = sqlSessionFactory.openSession(true);
            CacheMapper mapper1 = sqlSession1.getMapper(CacheMapper.class);
            System.out.println(mapper1.getEmpByEid(1));
            //关闭sqlSession或提交sqlSession
            sqlSession1.close();

            SqlSession sqlSession2 = sqlSessionFactory.openSession(true);
            CacheMapper mapper2 = sqlSession2.getMapper(CacheMapper.class);
            System.out.println(mapper2.getEmpByEid(1));
            //关闭sqlSession或提交sqlSession
            sqlSession2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
