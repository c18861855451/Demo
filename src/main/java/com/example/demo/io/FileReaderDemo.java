package com.example.demo.io;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileReaderDemo {
    public static void main(String[] args) throws IOException {

        String path="D:\\2.txt";
        FileReader fileReader =null;
        try {
            fileReader = new FileReader(path);
            char[] c=new char[8];
            int s=0;
            while((s=fileReader.read(c))!=-1){
                System.out.print(new String(c,0,s));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fileReader!=null){
                fileReader.close();
            }
        }

    }
}
