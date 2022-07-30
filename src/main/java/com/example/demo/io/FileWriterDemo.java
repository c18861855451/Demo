package com.example.demo.io;

import java.io.FileWriter;
import java.io.IOException;

public class FileWriterDemo {
    public static void main(String[] args) throws IOException {
        String path = "D:\\3.txt";
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(path);
            String s = "123456";
            fileWriter.write(s, 0, s.length());
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
        if(fileWriter!=null){
            fileWriter.close();
        }
        }
    }
}
