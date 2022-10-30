package com.atguigu.mybatis.test;

import com.atguigu.mybatis.mapper.DeptMapper;
import com.atguigu.mybatis.mapper.EmpMapper;
import com.atguigu.mybatis.pojo.Dept;
import com.atguigu.mybatis.pojo.Emp;
import com.atguigu.mybatis.utils.SqlSessionUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * @author xukai
 * @create 2022-08-07 23:01
 */
public class ResultMapTest {

    /**
     * 解决字段名与属性名不一致的情况：
     * a> 为表的字段起别名，保持和属性名的一致
     * b> 设置全局配置，将 自动映射为驼峰。
     *         <setting name="mapUnderscoreToCamelCase" value="true"/>
     * c> 通过 resultMap设置自定义的映射关系
                 <resultMap id="empResultMap" type="Emp">
                     <id property="eid" column="eid"></id>   <!-- id设置主键 -->
                     <result property="empName" column="emp_name"></result>
                     <result property="age" column="age"></result>
                     <result property="sex" column="sex"></result>
                     <result property="email" column="email"></result>
                 </resultMap>
     *
     *
     * 处理多对一的映射关系：
     * a> 级联属性赋值
     * b> association
     * c> 分布查询
     *
     *
     * 处理一对多的映射关系：
     * a> collection
     * b> 分布查询
     */

    /**
     * 分布查询的优点：可以实现延迟加载，但是必须在和核心配置文件中设置全局配置信息
     *      lazyLoadingEnabled：延迟加载的全局开关。当开启时，所有关联对象都会延迟加载
     *      aggressiveLazyLoading：当开启时，任何方法的调用都会加载该对象的所有属性。否则每个属性会按需加载
     */

    @Test
    public void testGetAllEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        List<Emp> empList = mapper.getAllEmp();
        for (Emp emp : empList){
            System.out.println(emp);
        }
    }
    @Test
    public void testGetEmpAndDept(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDept(1);
        System.out.println(emp);
    }
    @Test
    public void testGetEmpAndDeptByStep(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        EmpMapper mapper = sqlSession.getMapper(EmpMapper.class);
        Emp emp = mapper.getEmpAndDeptBySteOne(3);
//        System.out.println(emp);
        //开启了延迟加载
        System.out.println(emp.getEid());
        System.out.println("***********************************************");
        System.out.println(emp.getDept());
    }
    @Test
    public void testGetDeptAndEmp(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept= mapper.getDeptAndEmp(1);
        System.out.println(dept);
    }
    @Test
    public void testGetDeptAndEmpByStep(){
        SqlSession sqlSession = SqlSessionUtils.getSqlSession();
        DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        Dept dept = mapper.getDeptAndEmpByStepOne(1);
        System.out.println(dept.getDeptName());
        System.out.println("**********************************");
        System.out.println(dept);
    }


}
