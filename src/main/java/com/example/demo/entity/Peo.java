package com.example.demo.entity;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Peo {
    private int id;
    private String name;
    private String age;
}
