package com.example.demo.chat.client;

import com.example.demo.chat.common.handle.WriterHandle;
import com.example.demo.chat.common.handle.ReadHandle;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @ClassName ClientHandle
 * @Author CT
 * @Date 2022/5/31 9:34
 */
public class ClientHandle implements CompletionHandler<Void, AsynchronousSocketChannel> {
    /**
     * 连接上服务器将触发该方法
     *
     * @param result
     * @param asc
     */
    @Override
    public void completed(Void result, AsynchronousSocketChannel asc) {
        //读取数据
        ByteBuffer def = ByteBuffer.allocate(1024);
        asc.read(def,def,new ReadHandle(asc));
        //准备数据
        System.out.println("客户端已连接" + asc);
//        ByteBuffer buffer = ByteBuffer.wrap("客户端：你好，收到吗".getBytes());
//        asc.write(buffer,buffer,new WriterHandle(asc));
    }

    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel asc) {
        System.out.println("客户端连接失败 " + asc);
    }
}
