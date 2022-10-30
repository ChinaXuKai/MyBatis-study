package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.DynamicSQLMapper;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xukai
 * @create 2022-08-08 23:04
 */
public class DynamicSQLMapperTest {

    /**
     * 动态SQL：
     * 1、if：根据标签中test属性所对应的表达式决定标签中的内容是否需要拼接到SQL中
     * 2、where：
     *      当where标签中有内容时，会自动生成 where关键字，并且将内容前多余的 and或or 去掉
     *      当where标签中没有内容时，此时的where标签没有任何效果(不会自动生成 where关键字)
     * 注意：where 标签不能将其中内容后面多余的 and或or 去掉
     * 3、trim：
     * prefix / suffix：将trim标签中内容前面或后面添加指定内容
     * prefixOverrides / suffixOverrides：将trim标签中内容前面或后面去掉指定内容
     * 若标签中没有内容时，trim标签也没有任何效果
     * 4、choose、when、otherwise，when相当于if...else，otherwise相当于else
     * when最少得有一个，otherwise最多有一个
     * 5、foreach
     *      collection：设置需要循环的数组或集合
     *      item：表示数组或集合中的每一个数据
     *      separator：循环体之间的分隔符
     *      open：foreach标签所循环的所有内容的开始符
     *      close：foreach标签所循环的所有内容的结束符
     * 6、sql
     *      定义SQL片段：<sql id="empColumns">eid,emp_name,age,sex,email</sql>
     *      引用SQL片段：<include refid="empColumns"></include>
     */


    @Test
    public void testGetEmpByConditionOne(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> empList = mapper.getEmpByConditionOne(new Emp(null, "张三", null, "男", "123@qq.com"));
        System.out.println(empList);
    }
    @Test
    public void testGetEmpByConditionTwo(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> empList2 = mapper.getEmpByConditionTwo(new Emp(null, null, null, null, null));
        System.out.println("*******************************");
        System.out.println(empList2);
//        List<Emp> empList1 = mapper.getEmpByConditionTwo(new Emp(null, "张三", null, null, "123@qq.com"));
//        System.out.println(empList1);     //Preparing: select * from t_emp WHERE emp_name = ? and or email = ?
//        List<Emp> empList3 = mapper.getEmpByConditionTwo(new Emp(null, "张三", null, null, null));
//        System.out.println(empList3);     //Preparing: select * from t_emp WHERE emp_name = ? and
    }
    @Test
    public void testGetEmpByConditionThree(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> empList3 = mapper.getEmpByConditionThree(new Emp(null, "张三", null, null, null));
        System.out.println(empList3);     //解决getEmpByConditionTwo() 的 empList3
//        List<Emp> empList1 = mapper.getEmpByConditionTwo(new Emp(null, "张三", null, null, "123@qq.com"));
//        System.out.println(empList1);     //Preparing: select * from t_emp WHERE emp_name = ? and or email = ?
    }

    @Test
    public void testGetEmpByChoose(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        List<Emp> empList1 = mapper.getEmpByChoose(new Emp(null, "张三", null, null, "123@qq.com"));
        System.out.println(empList1);     //解决getEmpByConditionTwo() 的 empList1
        List<Emp> empList3 = mapper.getEmpByChoose(new Emp(null, "张三", null, null, null));
        System.out.println(empList3);
    }

    @Test
    public void testDeleteMoreByArray(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        int result = mapper.deleteMoreByArray(new Integer[]{6, 7, 8});
        System.out.println(result);
    }

    @Test
    public void testInsertMoreByArray(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DynamicSQLMapper mapper = sqlSession.getMapper(DynamicSQLMapper.class);
        Emp emp1 = new Emp(null, "xukai", 20, "男", "xukai@qq.com");
        Emp emp2 = new Emp(null, "hjy", 20, "女", "hjy@126.com");
        List<Emp> emps = Arrays.asList(emp1, emp2);
        int result = mapper.insertMoreByArray(emps);
        System.out.println(result);
    }
}
