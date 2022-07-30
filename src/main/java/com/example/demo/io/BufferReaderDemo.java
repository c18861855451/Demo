package com.example.demo.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BufferReaderDemo {
    public static void main(String[] args) {
        String path="D:\\2.txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String s=null;
            //readLine()整行读取，如果不为null就说明没读完
            while ((s=bufferedReader.readLine())!=null){
                System.out.println(s);
                bufferedReader.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
