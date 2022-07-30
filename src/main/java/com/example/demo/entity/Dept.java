package com.example.demo.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("dept")
public class Dept implements Serializable {

    private Integer id;
    private String deptName;
}
