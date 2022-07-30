package com.example.demo.dao;

import com.example.demo.entity.File;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileDao {
    List<File> all();
    int save(File file);
    File selectById(int id);
}
