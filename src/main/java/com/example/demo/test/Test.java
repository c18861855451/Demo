package com.example.demo.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Test {
    public static void main(String[] args) throws IOException {
        String parent ="D:\\mytemp";
        String child ="hello2.txt";
        File file = new File(parent);
        if(!file.exists()){
            if(file.mkdir()){
                System.out.println("创建成功！");
            }else {
                System.out.println("创建失败！");
            };

        }

        File file1 = new File(parent, child);
        if(!file1.exists()) {
            //创建文件
           if(file1.createNewFile()) {
               //文件输出对象
               FileWriter fileWriter = new FileWriter(file1);
               //处理流或包装流
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
               bufferedWriter.write("加油哦");
               bufferedWriter.flush();
               bufferedWriter.close();
           }
        }else {
            System.out.println("重复了！！ ");
        }
    }
}
