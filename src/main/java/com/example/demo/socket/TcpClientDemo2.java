package com.example.demo.socket;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClientDemo2 {
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket(InetAddress.getLocalHost(), 8909);
        //创建输出流
        OutputStream outputStream= socket.getOutputStream();

        FileInputStream fileInputStream=new FileInputStream(new File("C:\\Users\\c1886\\Pictures\\Saved Pictures\\a.jpg"));
        byte[] buffer=new byte[1024];
        int len;
        while ((len=fileInputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        fileInputStream.close();
        outputStream.close();
        socket.close();

    }


}
