package com.atguigu.mybatis.mapper;

import com.atguigu.mybatis.pojo.Emp;
import org.apache.ibatis.annotations.Param;

/**
 * @author xukai
 * @create 2022-08-09 11:12
 */
public interface CacheMapper {

    /**
     * 测试mybatis的一级缓存
     */
    Emp getEmpByEid(@Param("eid") Integer eid);


    void insertEmp(Emp emp);
}
