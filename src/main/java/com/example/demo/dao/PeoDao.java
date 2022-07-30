package com.example.demo.dao;

import com.example.demo.entity.Peo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PeoDao {
    Peo selectById();
}
