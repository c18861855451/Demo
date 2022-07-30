package com.example.demo.entity;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class File {
    private int id;
    private String oldName;
    private String newName;
    private String path;
    private String type;
    private String size;
    private String ext;
    private Date createTime;


}
