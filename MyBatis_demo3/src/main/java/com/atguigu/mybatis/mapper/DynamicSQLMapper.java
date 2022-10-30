package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xukai
 * @create 2022-08-08 22:30
 */
public interface DynamicSQLMapper {

    /**
     * 多条件查询
     */
    List<Emp> getEmpByConditionOne(Emp emp);

    List<Emp> getEmpByConditionTwo(Emp emp);

    List<Emp> getEmpByConditionThree(Emp emp);


    /**
     * 测试choose、when、otherwise
     */
    List<Emp> getEmpByChoose(Emp emp);


    /**
     * 通过数组实现批量删除
     */
    int deleteMoreByArray(@Param("eids") Integer[] edis);


    /**
     * 通过list集合实现批量添加
     */
    int insertMoreByArray(@Param("emps") List<Emp> emps);
}
