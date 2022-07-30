package com.example.demo.socket.udp;

import org.apache.logging.log4j.util.Strings;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.SQLOutput;

public class UdpClientDemo {
    //不需要连接服务器
    public static void main(String[] args) {
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length);
        try {
            //创建一个socket
            DatagramSocket datagramSocket = new DatagramSocket(8000);
            System.out.println("接收数据中。。。");
            datagramSocket.receive(datagramPacket);//往空包放数据
            String s = new String(datagramPacket.getData(), 0, datagramPacket.getLength());//数据转换字符串，偏移量从0到数据的长度
            System.out.println(s);//输出信息
            datagramSocket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
