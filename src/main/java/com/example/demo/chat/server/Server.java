package com.example.demo.chat.server;


import com.example.demo.chat.common.kit.Writer;
import com.example.demo.chat.common.run.ClientManger;
import com.example.demo.chat.common.run.GroupManger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

import static com.example.demo.chat.common.run.GroupManger.DEFAULT_GROUP_ID;

/**
 * @ClassName Server
 * @Author CT
 * @Date 2022/5/31 9:15
 */
public class Server {
    public static void main(String[] args) throws Exception {
        //打开服务器
        AsynchronousServerSocketChannel socketChannel = AsynchronousServerSocketChannel.open();
        //绑定主机
        SocketAddress address = new InetSocketAddress("127.0.0.1", 6666);
        socketChannel.bind(address);
        //接收客户端异步
        socketChannel.accept(socketChannel, new ServerHandle());

        while (true) {

            //获取键盘输入
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferedReader.readLine();
            if (s.equals("stop")) {
                break;
            }
            ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
            //获取客户端
//            System.out.println("请输入用户名");
//            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(System.in));
//            String cc = bufferedReader1.readLine();
//            AsynchronousSocketChannel[] asc = ClientManger.get(cc);  //指定人发送
            AsynchronousSocketChannel[] asc = GroupManger.get(DEFAULT_GROUP_ID);  //群发
            Writer.write(wrap, asc);
//        Thread.sleep(100000);

        }
    }

}