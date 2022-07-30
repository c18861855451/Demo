package com.example.demo.rabbitMq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


public class RabbitmqUtil {
    public static Channel getChannel() throws Exception {
        //创建一个连接工厂
        ConnectionFactory cf = new ConnectionFactory();
        //工厂ip连接rabbitMQ的队列
        cf.setHost("192.168.2.130");
        cf.setPort(5672);//默认
        cf.setVirtualHost("/");
        //用户名
        cf.setUsername("admin");
        //密码
        cf.setPassword("123456");
        //创建连接
//        // 2、通过连接工厂创建连接
//        cf.setConnectionTimeout(999999999);
//// 最关键就是下面这个语句
//        cf.setHandshakeTimeout(9999999);
        Connection connection = cf.newConnection();
        //获取信道
        Channel channel = connection.createChannel();
        return channel;
    }
}
