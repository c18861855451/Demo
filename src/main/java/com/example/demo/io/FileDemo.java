package com.example.demo.io;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) {
        //1.定义文件路径
//        String path = "D:\\1.txt";
//       // 在内存中创建对象
//        File file = new File(path);
//        try {
//            file.createNewFile();
//            System.out.println("创建成功！");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        new FileDemo().creat();

//        new FileDemo().creat2();

//        new FileDemo().info();
        new FileDemo().mulu();
    }

    //方式2：new File(File partent,String child) //根据父目录文件+子路径
    //D:\2.txt
    public void creat() {
        String parenPtath = "D:\\";
        File file = new File(parenPtath);
        String child = "2.txt";
        //在内存中创建对象
        File file1 = new File(file, child);
        try {
            file1.createNewFile();
            System.out.println("创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //方式3：new File(String partent,String child) //根据父目录+子路径
    //D:\2.txt
    public void creat2(){
        String parenPtath = "D:\\";
        String child = "3.txt";
        //在内存中创建对象
        File file1 = new File(parenPtath, child);
        try {
            file1.createNewFile();
            System.out.println("创建成功！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //获取文件的绝对路径
    public void info() {
        File file = new File("D:\\1.txt");
        System.out.println("文件的绝对路径"+file.getAbsolutePath());
        System.out.println("父级目录："+file.getParentFile());
        System.out.println("文件字节长度"+file.length());
        System.out.println("是否是文件"+file.isFile());
        System.out.println("是否是目录"+file.isDirectory());
        System.out.println("是否存在"+file.exists());
    }

    //创建一级目录和多级目录

    public void mulu(){
//        String yiji="D:\\yiji";
//        File file = new File(yiji);
//        if(file.exists()){
//            System.out.println("目录已存在");
//        }
//        else{
//            if(file.mkdir()){ //一级创建
//                System.out.println("创建成功");
//            }else {
//                System.out.println("创建失败");
//            }
//        }

        //多级
        String yiji1="D:\\yiji1\\erji\\sanji";
        File file1 = new File(yiji1);
        if(file1.exists()){
            System.out.println("目录已存在");
        }
        else{
            if(file1.mkdirs()){ //一级创建
                System.out.println("创建成功");
            }else {
                System.out.println("创建失败");
            }
        }
    }
}
