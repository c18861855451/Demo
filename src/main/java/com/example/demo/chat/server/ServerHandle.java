package com.example.demo.chat.server;

import com.example.demo.chat.common.bo.Client;
import com.example.demo.chat.common.handle.ReadHandle;
import com.example.demo.chat.common.run.ClientManger;
import com.example.demo.chat.common.run.GroupManger;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

import static com.example.demo.chat.common.run.GroupManger.DEFAULT_GROUP_ID;

/**
 * @ClassName ServerHandle
 * @Author CT
 * @Date 2022/5/31 9:23
 */
public class ServerHandle implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {


    /**
     * 有客户端连接将触发该方法
     * @param ac
     * @param ac
     */
    @Override
    public void completed(AsynchronousSocketChannel asc, AsynchronousServerSocketChannel ac) {
        //多客户端连接
        ac.accept(ac,new ServerHandle());
        //保存客户端
        Client client = ClientManger.put(asc);
        GroupManger.put(DEFAULT_GROUP_ID,client.getAsc());
        System.out.println("有客户端连接"+client);

        //获取默认缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //读取数据
        asc.read(buffer,buffer,new ReadHandle(asc));
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel ac) {
        System.out.println("连接出错"+ac);
    }
}
