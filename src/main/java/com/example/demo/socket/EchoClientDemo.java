package com.example.demo.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
//客户端
public class EchoClientDemo {
    public static void main(String[] args) throws IOException {
        //创建客户端socket对象，ip，端口
        Socket socket = new Socket("localhost", 6666);
        //获取socket输出流向服务器发送消息
        PrintStream printStream = new PrintStream(new BufferedOutputStream(socket.getOutputStream()));
        //获取socket的输入流，接收服务端的消息
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        printStream.println("我来啦");
        //刷新缓存
        printStream.flush();
        String s = bufferedReader.readLine();
        System.out.println("服务器说"+s);
        //关闭流
        printStream.close();
        bufferedReader.close();

    }
}
