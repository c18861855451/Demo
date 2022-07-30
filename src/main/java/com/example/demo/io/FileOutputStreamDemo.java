package com.example.demo.io;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileOutputStreamDemo {
    public static void main(String[] args) {

    }
    @Test
    public  void FileOFileOutput() throws IOException {
        String path="D:\\2.txt";
        FileOutputStream FileOutputStream =null;
        try {
            FileOutputStream = new FileOutputStream(path);
             String s="caotaojiayou";
            FileOutputStream.write(s.getBytes(),0,s.length());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            FileOutputStream.close();
        }
    }
}
