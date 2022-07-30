package com.example.demo.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BuffWriterDemo {
    public static void main(String[] args) {
        String path ="D:\\4.txt";
        try {
            //new FileWriter(path,true)追加的方式添加
            //new FileWriter(path) 覆盖的形式新增
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path,true));
            bufferedWriter.write("坚持就是胜利！！");
            bufferedWriter.newLine();//插入一个和系统相关换行
            bufferedWriter.write("相信明天！！");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
