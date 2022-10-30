package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xukai
 * @create 2022-08-07 22:56
 */
public interface EmpMapper {

    /**
     * 查询所有的员工信息
     */
    List<Emp> getAllEmp();


    /**
     * 查询员工以及员工所对应的部门信息
     */
    Emp getEmpAndDept(@Param("eid") Integer id);


    /**
     * 通过分布查询 查询员工以及员工所对应的部门信息
     * 分布查询 第一步：查询员工信息
     */
    Emp getEmpAndDeptBySteOne(@Param("eid") Integer eid);


    /**
     * 通过分布查询 查询部门以及部门中所有的员工信息
     * 分布查询第二步：根据did查询员工信息
     */
    List<Emp> getDeptAndEmpByStepTwo(@Param("did") Integer did);
}
