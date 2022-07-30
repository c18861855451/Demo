package com.example.demo.io;

import java.io.*;
//字节流操作二进制文件以及文本文件
public class Bufferd_copy2 {
    public static void main(String[] args) {
        String path="D:\\5.txt";
        String target="D:\\6.txt";
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(path));
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(target));
            int len;
            byte[] b=new byte[1024];
            while ((len=bufferedInputStream.read(b))!=-1){
                bufferedOutputStream.write(b,0,len);
            }
            bufferedOutputStream.flush();
            bufferedInputStream.close();
            bufferedOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
