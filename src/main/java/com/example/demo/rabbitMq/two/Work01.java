package com.example.demo.rabbitMq.two;

import com.example.demo.rabbitMq.RabbitmqUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * 工作线程
 * 消费者
 */
public class Work01 {
    public static final String QUEUE_NAME = "hello1";

    public static void main(String[] args) throws Exception {
        //创建信道
        Channel channel = RabbitmqUtil.getChannel();
        //消息的接收

        /**
         * 消费者消费消息
         * 1消费哪个队列
         * 2消费成功之后是否要自动应答，true代表的是自动答，flase，代表手动应答
         * 3消费者未成功消费的回调
         * 4消费者取消消费的回调
         */
        DeliverCallback var3 = (var1, var2) -> {
            byte[] body = var2.getBody();
            String s = new String(body);
            System.out.println(s);
        };
        channel.basicConsume(QUEUE_NAME, true, var3, var4 -> {
            System.out.println("回调了" + var4);
        });
        System.out.println("c2等待线程");
    }
}
