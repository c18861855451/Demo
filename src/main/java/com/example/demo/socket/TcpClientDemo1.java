package com.example.demo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TcpClientDemo1 {
    public static void main(String[] args) {
        Socket socket =null;
        OutputStream outputStreamw=null;
        try {
            //创建一个socket链接
             socket = new Socket("127.0.0.1", 8088);
            //发送消息io流
            OutputStream outputStream = socket.getOutputStream();
            //发送内容
            String s = "加油";
            outputStream.write(s.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStreamw!=null){
                try {
                    outputStreamw.close();
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
        }
    }
}
