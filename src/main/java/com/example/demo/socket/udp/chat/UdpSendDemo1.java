package com.example.demo.socket.udp.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Scanner;

public class UdpSendDemo1 {
    public static void main(String[] args) throws Exception {
        //创建流
        DatagramSocket datagramSocket = new DatagramSocket();
        //准备数据，控制台读取system in
//        Scanner scanner=new Scanner(System.in);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

            String s = bufferedReader.readLine();
            DatagramPacket datagramPacket = new DatagramPacket(s.getBytes(), 0, s.getBytes().length, new InetSocketAddress("127.0.0.1", 8077));
            //发送包
            datagramSocket.send(datagramPacket);
            if(s.equals("bye")){
                break;
            }
        }
        //关闭流
        datagramSocket.close();
    }
}
