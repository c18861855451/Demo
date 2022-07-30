package com.example.demo.dao;

import com.example.demo.entity.Dept;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserDao {

    User selectOneById(@Param("id") Integer id);

    Dept selectDeptById(@Param("did") Integer did);
}
