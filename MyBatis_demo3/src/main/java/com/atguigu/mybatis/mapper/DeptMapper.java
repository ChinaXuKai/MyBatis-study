package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Dept;
import org.apache.ibatis.annotations.Param;

/**
 * @author xukai
 * @create 2022-08-07 22:56
 */
public interface DeptMapper {

    /**
     * 通过分布查询 查询员工以及员工所对应的部门信息
     * 分布查询 第二步：通过 did 查询员工所对应的部门
     */
    Dept getEmpAndDeptByStepTwo(@Param("did") Integer did);


    /**
     * 获取部门以及部门中所有的员工信息
     * @return
     */
    Dept getDeptAndEmp(@Param("did") Integer did);


    /**
     * 通过分布查询 查询部门以及部门中所有的员工信息
     * 分布查询第一步：查询部门信息
     */
    Dept getDeptAndEmpByStepOne(@Param("did") Integer did);

}
