package com.example.demo.test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) throws IOException {
        String path ="D:\\mytemp\\hello.txt";
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String s=null;
        int i=0;
        while ((s= bufferedReader.readLine())!=null){

            System.out.println("第"+i+"行"+s);
              i++;
        }


    }
}
