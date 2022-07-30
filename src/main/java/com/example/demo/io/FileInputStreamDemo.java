package com.example.demo.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileInputStreamDemo {
    public static void main(String[] args) {

    }

    @Test
    public void FileInputStream() throws IOException {
        String path="D:\\1.txt";
        FileInputStream fileInputStream = null;
        try {
             fileInputStream = new FileInputStream(path);
             byte[] bytes=new byte[1024];
             int len=0;
             while ((len=fileInputStream.read(bytes))!=-1){
                 System.out.println(new String(bytes,0,len));
             }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fileInputStream.close();
        }

    }
}
