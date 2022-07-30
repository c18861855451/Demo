package com.example.demo.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
//对象序列化和反序列化
public class ObjectOutputStreamDemo {
    public static void main(String[] args) {
        String path="D:\\8.txt";
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(path));
            objectOutputStream.writeBoolean(true);
            objectOutputStream.writeInt(100);
            objectOutputStream.writeUTF("加油，今天又是新的一天！");
            objectOutputStream.flush();
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
