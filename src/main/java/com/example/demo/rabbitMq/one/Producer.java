package com.example.demo.rabbitMq.one;

import com.example.demo.rabbitMq.RabbitmqUtil;
import com.rabbitmq.client.Channel;

/**
 * @ClassName Producer
 * @Author CT
 * @Date 2022/6/10 11:13
 */
public class Producer {
    private final static String QUEUE_NAME = "hello";

    //发消息
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitmqUtil.getChannel();
        //生成队列
        /**
         * 生成一个队列
         * 1队列名称
         * 2队列里面的消息是否持久化（存储在磁盘上），默认情况消息存储在内存中
         * 3该队列是否只供一个消费者进行消费，是否进行消息共享，true可以多个消费者消费
         * 4是否自动删除，最后一个消费者端连接以后，该队列是否自动删除
         * 5其他参数
         */
        //队列声明
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        String msg = "hello word";
        /**
         * 发送一个消息
         * 1发送消息到哪个交换机
         * 2路由的key值是哪个，本次是队列的名称
         * 3其他参数信息
         * 4发送消息的消息体
         */
        channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
        System.out.println("发送信息完毕");

    }
}
