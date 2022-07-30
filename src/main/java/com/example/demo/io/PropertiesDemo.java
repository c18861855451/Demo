package com.example.demo.io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PropertiesDemo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources\\application.properties"));
        String s =null;
        while ((s=bufferedReader.readLine())!=null){
            String[] split = s.split("=");
//            System.out.println(split[0]+"值是"+split[1]);
            System.out.println(split[0]);
        }
    }
}
