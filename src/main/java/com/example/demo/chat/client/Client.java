package com.example.demo.chat.client;

import com.example.demo.chat.common.handle.WriterHandle;
import com.example.demo.chat.common.kit.Writer;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @ClassName Client
 * @Author CT
 * @Date 2022/5/31 9:29
 */
public class Client {
    public static void main(String[] args) throws Exception {
        //打开客户端
        AsynchronousSocketChannel asc = AsynchronousSocketChannel.open();
        //连接服务器（异步）
        SocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        asc.connect(address, asc, new ClientHandle());

        //获取用户输入信息
        while (true) {
            //获取输入流
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferedReader.readLine();
            if ("stop".equals(s)) {
                break;
            }
            ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
            Writer.write(wrap, asc);
//        Thread.sleep(100000);
        }
    }

}
