package com.example.demo.socket.udp.chat;

import com.spire.pdf.exporting.xps.schema.Break;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServerDemo1 {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(8077);
        while (true) {
            //接受数据
            byte[] bytes = new byte[1024];
            DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
            //接受数据
            datagramSocket.receive(datagramPacket);
            //byebye,断开连接
            byte[] data = datagramPacket.getData();
            String s = new String(data, 0, datagramPacket.getLength());
            System.out.println(s);
            if (s.equals("bye")) {
                break;
            }


        }
        datagramSocket.close();
    }
}
