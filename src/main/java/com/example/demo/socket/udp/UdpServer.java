package com.example.demo.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
//等待客户端连接
public class UdpServer {
    public static void main(String[] args) throws Exception {
        //开放端口
        DatagramSocket datagramSocket=new DatagramSocket(8060);
        //接受数据包
        byte[] bytes = new byte[1024];
        DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);

        datagramSocket.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData(),0,datagramPacket.getLength()));
        //关闭流
        datagramSocket.close();
    }
}
