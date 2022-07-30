package com.example.demo.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ObjectInputStreamDemo {

    public static void main(String[] args) {
        String path="D:\\8.txt";
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(path));
            System.out.println( objectInputStream.readBoolean());
            System.out.println(objectInputStream.readInt());
            System.out.println(objectInputStream.readUTF());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
