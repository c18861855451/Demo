package com.example.demo.io;

import java.io.*;
import java.util.Properties;

public class PropertiesDemo2 {
    public static void main(String[] args) throws IOException {
        Properties properties=new Properties();
        properties.load(new FileReader("src/main/resources\\application.properties"));
        properties.list(System.out);

        //获取key对应的值
        String property = properties.getProperty("server.port");
        System.out.println(property);
        //没有就创建，有就修改
        properties.setProperty("server.port","8086");
        //保存
        properties.store(new FileWriter("src/main/resources\\application.properties"),null);
        properties.store(new FileOutputStream("src/main/resources\\application.properties"),null);

    }
}
