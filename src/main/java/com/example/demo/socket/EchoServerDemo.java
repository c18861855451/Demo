package com.example.demo.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.ProviderMismatchException;
//服务端
public class EchoServerDemo {
    public static void main(String[] args) throws IOException {
        //创建一个服务器端的socket（1024-65535），端口
        ServerSocket server=new ServerSocket(6666);
        System.out.println("服务正在启动，等待连接");
        //如果连接上了就会返回一个socket对象
        Socket socket = server.accept();
        System.out.println("已连接，IP为"+socket.getInetAddress().getHostAddress());
        //拿到输入流字节流
        InputStream inputStream = socket.getInputStream();
        //字节流转字符流，通过InputStreamReader
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //从字符输入流中读取文本，缓冲各个字符，从而提供字符、数组和行的高效读取
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        //读出信息
        String s = bufferedReader.readLine();
        System.out.println("客户端说"+s);
        //获取输出流，向客户端返回消息
        OutputStream outputStream = socket.getOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        PrintStream printStream = new PrintStream(bufferedOutputStream);
        printStream.println("好的");
        //刷新缓冲区
        printStream.flush();
        //关闭流
        printStream.close();
    }
}
