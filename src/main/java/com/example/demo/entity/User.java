package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@Alias("user")
public class User implements Serializable {
    private static final long serialVersionUID = 2703059950988452404L;
    private Integer id;
    private String name;
    private Integer age;
    private String adress;
    private String password;
    private Integer did;

    private Dept dept;

}
