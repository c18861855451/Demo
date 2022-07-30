package com.example.demo.chat.common.handle;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.StandardCharsets;

/**
 * @ClassName ReadHandle
 * @Author CT
 * @Date 2022/5/31 10:04
 */
public class ReadHandle implements CompletionHandler<Integer, ByteBuffer> {
    private final AsynchronousSocketChannel asc;

    public ReadHandle(AsynchronousSocketChannel asc) {
        this.asc=asc;
    }

    @Override
    public void completed(Integer result, ByteBuffer buffer) {

        System.out.println(new String(buffer.array(), 0, result));
//        //自动回复
//        ByteBuffer wrap = ByteBuffer.wrap("服务端：收到了".getBytes());
//        asc.write(wrap,wrap,new WriterHandle(asc));

        //继续读取数据
        //默认大小的缓冲区
        ByteBuffer def = ByteBuffer.allocate(1024);
        asc.read(def,def,new ReadHandle(asc));
    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        System.out.println("读取失败！");
    }
}
