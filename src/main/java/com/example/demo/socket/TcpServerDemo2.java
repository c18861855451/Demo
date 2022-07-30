package com.example.demo.socket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo2 {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket=new ServerSocket(8909);
        //等待链接
        Socket socket = serverSocket.accept();
        //获取输入流
        InputStream inputStream = socket.getInputStream();
        //文件输出
        FileOutputStream fileOutputStream=new FileOutputStream(new File("E:\\upload\\recive"));
        byte[] bytes = new byte[1024];
        int len;
        while ((len=inputStream.read())!=-1){
            fileOutputStream.write(bytes, 0, len);
        }
        fileOutputStream.close();
        inputStream.close();
        socket.close();
        serverSocket.close();
    }
}
