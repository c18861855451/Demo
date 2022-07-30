package com.example.demo.chat.common.handle;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @ClassName writerHandle
 * @Author CT
 * @Date 2022/5/31 9:51
 */
public class WriterHandle implements CompletionHandler<Integer, ByteBuffer> {

    private final AsynchronousSocketChannel asc;


    public WriterHandle(AsynchronousSocketChannel asc) {
        this.asc = asc;
    }

    /**
     * 写出成功将触发该方法
     *
     * @param result 已发送数据大小
     * @param buffer
     */
    @Override
    public void completed(Integer result, ByteBuffer buffer) {
        //判断是否还有数据，有就是true
        if (buffer.hasRemaining()) {
            asc.write(buffer, buffer, this);
        } else {
            System.out.println("写出成功");
        }

    }

    @Override
    public void failed(Throwable exc, ByteBuffer buffer) {
        System.out.println("写出失败");
    }
}
