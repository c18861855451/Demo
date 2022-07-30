package com.example.demo.socket.udp.chat;

import javax.annotation.security.RunAs;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TalkRecevice implements Runnable {
    DatagramSocket datagramSocket = null;
    private int port;
    private String msgfrom;
    DatagramPacket datagramPacket = null;

    public TalkRecevice(int port,String msgfrom) {
        this.port = port;
        this.msgfrom=msgfrom;
        try {
            datagramSocket = new DatagramSocket(port);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (true) {
            try {
                //接受数据
                byte[] bytes = new byte[1024];
                DatagramPacket datagramPacket = new DatagramPacket(bytes, 0, bytes.length);
                //接受数据
                datagramSocket.receive(datagramPacket);
                //byebye,断开连接
                byte[] data = datagramPacket.getData();
                String s = new String(data, 0, datagramPacket.getLength());

                System.out.println(msgfrom+s);
                if (s.equals("bye")) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        datagramSocket.close();
    }
}

