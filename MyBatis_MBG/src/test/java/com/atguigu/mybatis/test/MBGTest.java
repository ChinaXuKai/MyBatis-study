package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.pojo.EmpExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author xukai
 * @create 2022-08-09 16:08
 */
public class MBGTest {

    @Test
    public void testMBG(){
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = sqlSessionFactory.openSession(true);
            EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
            //查询所有数据
//            List<Emp> empList = mapper.selectByExample(null);
//            empList.forEach(System.out::println);
            //根据条件查询
//            EmpExample empExample = new EmpExample();
//            empExample.createCriteria().andEmpNameEqualTo("xukai").andAgeGreaterThanOrEqualTo(18);
//            empExample.or().andDidIsNotNull();
//            List<Emp> empList = mapper.selectByExample(empExample);
//            empList.forEach(emp -> System.out.println(emp));
            //修改功能
            //Preparing: update t_emp set emp_name = ?, age = ?, sex = ?, email = ?, did = ? where eid = ?
//            mapper.updateByPrimaryKey(new Emp(9, "yrg", null, "男", "123@qq.com", null));
            //Preparing: update t_emp SET emp_name = ?, sex = ?, email = ? where eid = ?
            mapper.updateByPrimaryKeySelective(new Emp(9, "yrg", null, "男", "123@qq.com", null));


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
