package com.example.demo.chat.common.kit;

import com.example.demo.chat.common.handle.WriterHandle;
import org.junit.experimental.theories.FromDataPoints;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * @ClassName Writer
 * @Author CT
 * @Date 2022/5/31 10:53
 */
public class Writer {
    /**
     * 发送消息处理器
     * @param buffer
     * @param clients 接收的客户段集合
     */
    public  static void write(ByteBuffer buffer, AsynchronousSocketChannel... clients){
        for (int i = 0; i < clients.length; i++) {
            AsynchronousSocketChannel asc = clients[i];
            //发送信息
            asc.write(buffer,buffer,new WriterHandle(asc));
        }

    }
}
