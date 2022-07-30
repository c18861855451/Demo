package com.example.demo.socket.udp.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable {
    DatagramSocket datagramSocket = null;
    BufferedReader bufferedReader = null;
    private int fromport;
    private String toip;
    private int toport;

    public TalkSend(int fromport, String toip, int toport) {
        this.fromport = fromport;
        this.toip = toip;
        this.toport = toport;
        try {
            //创建流
            datagramSocket = new DatagramSocket(fromport);
            //准备数据，控制台读取system in
//        Scanner scanner=new Scanner(System.in);
            bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {


        while (true) {
            try {
                String s = bufferedReader.readLine();
                DatagramPacket datagramPacket = new DatagramPacket(s.getBytes(), 0, s.getBytes().length, new InetSocketAddress(this.toip, this.toport));
                //发送包
                datagramSocket.send(datagramPacket);
                if (s.equals("bye")) {
                    break;
                }
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        //关闭流
        datagramSocket.close();
    }
}
