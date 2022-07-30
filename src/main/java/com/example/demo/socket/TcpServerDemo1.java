package com.example.demo.socket;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServerDemo1 {
    public static void main(String[] args)  {
        ServerSocket serverSocket = null;
        Socket socket =null;
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            //监听端口
            serverSocket = new ServerSocket(8088);
            //等待客户端连接过来
             socket = serverSocket.accept();
            //读取客户端的消息
            inputStream = socket.getInputStream();

//            byte[] bytes=new byte[1024];
//            int len=0;
//            while ((len=inputStream.read(bytes))!=-1){
//                String s = new String(bytes, 0, len);
//                System.out.println(s);
//            }
            //管道流
            byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                byteArrayOutputStream.write(bytes, 0, len);
            }
            System.out.println(byteArrayOutputStream.toString());
            //关闭流
            byteArrayOutputStream.close();
            inputStream.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(socket!=null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}