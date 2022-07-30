package com.example.demo.rabbitMq.one;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName Consumer
 * @Author CT
 * @Date 2022/6/10 17:16
 *
 * 消费者，接收消息
 */
public class Consumer {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //rabbitmqip
        connectionFactory.setHost("192.168.2.130");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        //账户
        connectionFactory.setUsername("admin");
        //密码
        connectionFactory.setPassword("123456");
        //创建连接
        Connection connection = connectionFactory.newConnection();
        //创建信道
        Channel channel = connection.createChannel();
        /**
         * 消费者消费消息
         * 1消费哪个队列
         * 2消费成功之后是否要自动应答，true代表的是自动答，flase，代表手动应答
         * 3消费者未成功消费的回调
         * 4消费者取消消费的回调
         */
        //声明接收消息
        DeliverCallback deliverCallback=(consumerTag,var2)->{
            String s = new String(var2.getBody());
            System.out.println(s);
        };
        //取消消息时候的回调
        CancelCallback cancelCallback=consumerTag->{
            System.out.println("消息被终端");
        };

        channel.basicConsume(QUEUE_NAME,true ,deliverCallback,cancelCallback);

    }
}
