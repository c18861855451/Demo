package com.example.demo.io;

import java.io.*;

public class Bufferd_copy {
    public static void main(String[] args) {
        String path="D:\\4.txt";
        String target="D:\\5.txt";
        BufferedReader bufferedReader =null;
        BufferedWriter bufferedWriter =null;
        try {
             bufferedReader = new BufferedReader(new FileReader(path));
             bufferedWriter = new BufferedWriter(new FileWriter(target));
             String len=null;
             //.readLine()读取一行内容但是没有换行
             while((len=bufferedReader.readLine())!=null){
                 bufferedWriter.write(len);
                 bufferedWriter.newLine();//
             }
            bufferedWriter.flush();
            bufferedReader.close();
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
