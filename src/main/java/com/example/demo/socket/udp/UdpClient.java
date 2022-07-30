package com.example.demo.socket.udp;

import org.springframework.web.util.pattern.PathPattern;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpClient {
    public static void main(String[] args) throws Exception {
        //建立一个socket
        DatagramSocket datagramSocket=new DatagramSocket();
        //建立包
        String s ="加油!!!";
        InetAddress localhost = InetAddress.getByName("localhost");
        DatagramPacket datagramPacket=new DatagramPacket(s.getBytes(StandardCharsets.UTF_8),0,s.getBytes().length,localhost,8060);
        //发送包
        datagramSocket.send(datagramPacket);
        //关闭流
        datagramSocket.close();
    }
}
