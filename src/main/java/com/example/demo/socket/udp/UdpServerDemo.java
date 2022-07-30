package com.example.demo.socket.udp;

import java.io.IOException;
import java.net.*;

public class UdpServerDemo {
    public static void main(String[] args) {
        String info = "day day 工作";
        byte[] bytes = info.getBytes();
        try {
            //打包数据，数据字节，数据偏移量，
            DatagramPacket datagramPacket = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"), 8000);
            //自己接收端口
            DatagramSocket datagramSocket = new DatagramSocket(9000);
            //发送数据
            datagramSocket.send(datagramPacket);
            //关闭流
            datagramSocket.close();
        } catch (UnknownHostException | SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
